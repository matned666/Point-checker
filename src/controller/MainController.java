package controller;

import dialog.Dialog;
import fx.DrawingArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML public Label pointX;
    @FXML public Label pointY;
    @FXML public Label pointId;
    @FXML public AnchorPane drawingArea;

    @FXML public void onAdPointButtonClicked(ActionEvent event) {
        Dialog.openDialog("Add point", "addPoint");
    }

    @Override public void initialize(URL location, ResourceBundle resources) {
        DrawingArea area = DrawingArea.getInstance();
        area.setArea(drawingArea);
        area.setPointId(pointId);
        area.setPointX(pointX);
        area.setPointY(pointY);
    }
}
