package controller;

import dialog.Dialog;
import fx.DrawingArea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public Label pointX;
    @FXML
    public Label pointY;
    @FXML
    public Label mouseX;
    @FXML
    public Label mouseY;
    @FXML
    public Label pointId;
    @FXML
    public StackPane drawingArea;
    @FXML
    public AnchorPane sceneA;
    @FXML
    public Label translateX;
    @FXML
    public Label translateY;
    @FXML
    public Label centerX;
    @FXML
    public Label centerY;

    private final DrawingArea drawingAreaInstance = DrawingArea.getInstance();

    @FXML
    public void onAdPointButtonClicked() {
        Dialog.openDialog("Add point", "addPoint", drawingArea.getScene());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawingAreaInstance.init(drawingArea, pointX, pointY, translateX, translateY, centerX, centerY, pointId);
        drawingArea.setOnMouseMoved(this::updateMouseLabels);
        drawingArea.setOnMouseDragged(this::updateMouseLabels);
    }

    private void updateMouseLabels(MouseEvent mouse) {
        double tx = mouse.getX();
        double ty = mouse.getY();
        mouseX.setText(String.valueOf(tx));
        mouseY.setText(String.valueOf(ty));
    }

    @FXML
    public void onRemoveButton() {
        DrawingArea drawingArea = DrawingArea.getInstance();
        this.drawingArea.getChildren().remove(drawingArea.getActuallySelected());
        drawingArea.removeActuallySelected();
    }
}
