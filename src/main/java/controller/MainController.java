package controller;

import dialog.Dialog;
import fx.DrawingArea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Label pointX;
    @FXML
    private Label pointY;
    @FXML
    private Label mouseX;
    @FXML
    private Label mouseY;
    @FXML
    private Label pointId;
    @FXML
    private StackPane drawingArea;
    @FXML
    private AnchorPane sceneA;
    @FXML
    private Label translateX;
    @FXML
    private Label translateY;
    @FXML
    private Label centerX;
    @FXML
    private Label centerY;

    private final DrawingArea drawingAreaInstance = DrawingArea.getInstance();
    @FXML
    private Label drawingAreaWidth;
    @FXML
    private Label drawingAreaHeight;

    @FXML
    public void onAdPointButtonClicked() {
        Dialog.openDialog("Add point", "addPoint", drawingArea.getScene());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawingAreaInstance.init(drawingArea, pointX, pointY, translateX, translateY, centerX, centerY, pointId, drawingAreaHeight, drawingAreaWidth);
        drawingArea.setOnMouseMoved(this::updateMouseLabels);
        drawingArea.setOnMouseDragged(this::updateMouseLabels);
        drawingAreaWidth.setText(String.valueOf(drawingArea.getWidth()));
        drawingAreaHeight.setText(String.valueOf(drawingArea.getHeight()));
        drawingAreaInstance.update();
        sceneA.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().name().equals("DELETE")) {
                drawingAreaInstance.removeActuallySelected();
            }
        });

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
