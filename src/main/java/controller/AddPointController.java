package controller;

import fx.AngleCurve;
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

    @FXML public CheckBox line;
    @FXML public CheckBox angle;
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
        Color fromPicker = colorPicker.getValue();
        Point point = Point.Builder.withCoordinates(x, y).withColor(fromPicker).withId(id).build();
        point.update();
        if (DrawingArea.getInstance().possibleToMakeLine()) {
            addLine(drawingArea, DrawingArea.getInstance().getActuallySelected(), point);
        }

        if (DrawingArea.getInstance().possibleToMakeAngle()) {
            addAngleArc(drawingArea, DrawingArea.getInstance().getActuallySelected(),
                    DrawingArea.getInstance().previousActuallySelected(), point);
            addLine(drawingArea, DrawingArea.getInstance().getActuallySelected(), point);
        }
        drawingArea.addPoint(point);
        drawingArea.update();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void addLine(DrawingArea drawingArea, Point point1, Point point2) {
        if (line.isSelected()) {
            DecorationLine line = new DecorationLine(point1, point2);
            line.setStroke(colorPicker.getValue());
            point1.subscribe(line);
            point2.subscribe(line);
            drawingArea.showDecoration(line);
        }
    }

    private void addAngleArc(DrawingArea drawingArea, Point tip, Point point1, Point point2) {
        if (angle.isSelected()) {
            line.setSelected(true);
            addLine(drawingArea, tip, point2);
            addLine(drawingArea, tip, point1);
            AngleCurve angleCurve = new AngleCurve(tip, point1, point2);
            angleCurve.setStroke(colorPicker.getValue());
            tip.subscribe(angleCurve);
            point1.subscribe(angleCurve);
            point2.subscribe(angleCurve);
            drawingArea.showDecoration(angleCurve);
        }
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

    @FXML
    private void fieldAction(ActionEvent event) {
        onApplyPointClick(event);
    }
}
