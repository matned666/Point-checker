//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ThreadFactory;
//
//import com.google.common.util.concurrent.ThreadFactoryBuilder;
//
//import io.reactivex.Scheduler;
//import io.reactivex.schedulers.Schedulers;
//
//public class ThreadTest {
//    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ThreadTest.class);
//
//    private static final String NAME = "Exhibeon";
//    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder()
//            .setThreadFactory(Executors.defaultThreadFactory())
//            .setNameFormat(NAME)
//            .build();
//    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor(THREAD_FACTORY);
//    private static final Scheduler SCHEDULER = Schedulers.from(EXECUTOR);
//
//    public static void execute(Runnable command) {
//        if (LOGGER.isDebugEnabled()) {
//            EXECUTOR.execute(new TaskWrapper(command).getRunnable());
//        } else {
//            EXECUTOR.execute(command);
//        }
//    }
//
//    public static Scheduler scheduler() {
//        return SCHEDULER;
//    }
//
//    public static ExecutorService executor() {
//        return EXECUTOR;
//    }
//
//    private static class TaskWrapper {
//
//        private final Runnable action;
//        private String id;
//
//        public TaskWrapper(Runnable action) {
//            this.action = action;
//            StringBuilder builder = new StringBuilder();
//            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
//            int min = Math.min(6, stackTrace.length);
//            for (int i = 3; i < min; i++) { //3 first are coming from this class and Exhibeon class
//                String[] split = stackTrace[i].getClassName().split("\\.");
//                builder.append("->")
//                        .append(split[split.length - 1])
//                        .append(":")
//                        .append(stackTrace[i].getMethodName())
//                        .append(":")
//                        .append(stackTrace[i].getLineNumber());
//            }
//            this.id = builder.toString();
//        }
//
//        public Runnable getRunnable() {
//            return () -> {
//                startTaskLog();
//                action.run();
//                endTaskLog();
//            };
//        }
//
//        private void startTaskLog() {
//            LOGGER.debug("START TASK " + id);
//        }
//
//        private void endTaskLog() {
//            LOGGER.debug("END TASK " + id);
//        }
//    }
//}