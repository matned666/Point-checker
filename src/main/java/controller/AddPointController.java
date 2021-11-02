package controller;

import fx.DecorationLine;
import fx.DrawingArea;
import fx.Point;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPointController implements Initializable {

    @FXML private ColorPicker colorPicker;
    @FXML private TextField xTextField;
    @FXML private TextField yTextField;
    @FXML private TextField idTextField;

    @FXML private CheckBox translate;

    @FXML public void onApplyPointClick(ActionEvent event) {
        DrawingArea drawingArea = DrawingArea.getInstance();
        double x;
        double y;
        x = !xTextField.getText().equals("") ?
                Double.parseDouble(xTextField.getText()) :
                drawingArea.getArea().getWidth() / 2;
        y = !yTextField.getText().equals("") ?
                Double.parseDouble(yTextField.getText()) :
                drawingArea.getArea().getHeight() / 2;

        if (translate.isSelected()) {
            x += drawingArea.getArea().getWidth() / 2;
            y += drawingArea.getArea().getHeight() / 2;
        }
        String id = idTextField.getText();
        Color color = colorPicker.getValue();
        Point point = Point.PointBuilder.aPoint(x, y)
                .withColor(color)
                .withId(id)
                .build();
        point.update();
        if (DrawingArea.getInstance().possibleToMakeLine()) {
            DecorationLine line = new DecorationLine(DrawingArea.getInstance().getActuallySelected(), point);
            drawingArea.getArea();
            DrawingArea.getInstance().getActuallySelected().subscribeLine(line);
            point.subscribeLine(line);
            drawingArea.showLine(line);
        }
        drawingArea.addPoint(point);
        drawingArea.update();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override public void initialize(URL location, ResourceBundle resources) {
        DrawingArea drawingArea = DrawingArea.getInstance();
        if (drawingArea.getActuallySelected() == null) {
            colorPicker.setValue(Color.BLACK);
        } else {
            colorPicker.setValue(drawingArea.getActuallySelected().getColor());
        }
        xTextField.setOnKeyReleased(key -> {
            String txt = xTextField.getText();
            try {
                double check = Double.parseDouble(txt);
            } catch (Exception ignored) {
                String backup = txt.length() > 1 ? xTextField.getText().substring(0, txt.length() - 1) : "";
                xTextField.setText(backup);
            }
        });

        yTextField.setOnKeyReleased(key -> {
            String txt = yTextField.getText();
            try {
                double check = Double.parseDouble(txt);
            } catch (Exception ignored) {
                String backup = txt.length() > 1 ? yTextField.getText().substring(0, txt.length() - 2) : "";
                yTextField.setText(backup);
            }
        });

    }
}
