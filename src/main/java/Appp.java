import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class Appp extends Application {

    @Override public void start(Stage primaryStage) throws Exception {
        Label label = new Label("asdafasdf");
        VBox vBox = new VBox(label);
        StackPane stackPane = new StackPane(vBox);
        vBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        primaryStage.setScene(new Scene(stackPane, 400 , 400));
        primaryStage.show();

        new Thread(()->{
            Bounds bounds = getBounds(new VBox(new Label("asdafasdf")));
            System.out.println(bounds);
        }).start();


        Platform.runLater(()-> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("h:"+vBox.getHeight() + ",w:" + vBox.getWidth());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    public static Bounds getBounds(Node node) {
        Group gr = new Group(node);
        new Scene(gr);
        gr.applyCss();
        gr.layout();
        return gr.getLayoutBounds();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
