import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

import javafx.application.Platform;

/**
 * Klasa zarządzająca wykonywaniem zadań
 */
public final class FXThreadTest {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(FXThreadTest.class);
    private static final String FX_THREAD_PREFIX = "FX";

    private FXThreadTest() {
    }

    /**
     * Metoda zarządzająca kolejnością wykonywania zadań Jeśli zadanie zgłosił
     * wątek fx, wykonywane jest ono od razu. Jeśli zadanie zgłosił wątek
     * zewnętrzny, wykonanie zlecane jest do wątku FX i wykonywane
     * asynchronicznie. Metod kończy przetwarzanie zaraz po przekazaniu zadania.
     *
     * @see FXThreadTest#runAndWait(Runnable) - tak samo jak run tylko czeka na realizację podanego {@link Runnable}
     * 
     * @param runnable
     *            zadanie do wykonania
     */
    public static void run(Runnable runnable) {
        if (Platform.isFxApplicationThread()) {
            runnable.run();
        } else {
            if (!FXThreadTest.isFxThreadAlive()) {
                LOGGER.warn("Nie udało się zamknac aplikacji - wątek GUI jest nieaktywny - aplikacja działa w tle.");
            }

            final Throwable stackTrace = DeferredStackTrace.getCurrent();
            Platform.runLater(() -> DeferredStackTrace.run(runnable, stackTrace));
        }
    }

    public static <T> T supplyAndWait(Supplier<T> supplier) {
        final CompletableFuture<T> future = new CompletableFuture<>();
        runAndWait(() -> {
            try {
                future.complete(supplier.get());
            } catch (Exception e) {
                LOGGER.error("", e);
                future.completeExceptionally(e);
            }
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("", e);
            return null;
        }
    }

    /**
     * Metoda przenosząca wykonywanie zadania na później (dotyczy akcji po
     * przerysowaniu layout'u)
     *
     * @param runnable
     *            zadanie do wykonania
     */
    public static void runLater(Runnable runnable) {
        final Throwable stackTrace = DeferredStackTrace.getCurrent();
        Platform.runLater(() -> DeferredStackTrace.run(runnable, stackTrace));
    }

    public static boolean isFxThreadAlive() {
        if (isFxThreadAlive_UsingTooklit()) {
            return true;
        }

        if (isFxThreaAlive_UsingName()) {
            return true;
        }
        String message = "FX GUI Thread is not alive. Closing application. System.exit(4)";
        LOGGER.error(message);
        System.exit(4);
        message = "System.exit() not working here. Close application from task manager.";
        LOGGER.error(message);
        return false;
    }

    private static boolean isFxThreadAlive_UsingTooklit() {
        Thread[] tarray = new Thread[Thread.activeCount()];
        Thread.enumerate(tarray);
        for (Thread thread : tarray) {
            if (MyToolkitHelper.isFxUserThread(thread)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isFxThreaAlive_UsingName() {
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        Thread k;
        for (Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
            k = entry.getKey();
            if (k.getName().contains("FX")) {
                return true;
            }
        }
        return false;
    }

    /**
     * It uses {@link FXThreadTest#run(Runnable)} method to run given {@link Runnable}
     * and waits until job is done (in FX thread).
     * 
     * @param runnable
     * @category unit tests
     */
    public static void runAndWait(Runnable runnable) {
        final CountDownLatch cdl = new CountDownLatch(1);
        FXThreadTest.run(() -> {
            try {
                runnable.run();
            } finally {
                cdl.countDown();
            }
        });
        try {
            cdl.await();
        } catch (InterruptedException e) {
            //not interested
        }
    }

    public static void runInNextPulse(Runnable runnable) {
        Platform.runLater(() -> Platform.runLater(() -> runnable.run()));
    }
}
