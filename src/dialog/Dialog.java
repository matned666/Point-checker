package dialog;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Dialog {


    public static void openDialog(String title, String fileName, Scene scene){

        AnchorPane parent = (AnchorPane) setParent(fileName);
        nextStage(title+" screen", parent, scene);
    }

    private static Node setParent(String file) {
        FXMLLoader fxmlLoader = new FXMLLoader(Dialog.class
                .getResource("/"+file+".fxml"));
        AnchorPane parent;
        try {
            parent = fxmlLoader.load();
            return parent;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void nextStage(String title, AnchorPane parent, Scene mainScene) {
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setResizable(false);
        Window window = mainScene.getWindow();
        stage.setX(window.getX());
        stage.setY(window.getY()+350);
        stage.show();
    }
}