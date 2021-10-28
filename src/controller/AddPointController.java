package controller;

import fx.DrawingArea;
import fx.Point;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPointController implements Initializable {

    @FXML public ColorPicker colorPicker;
    @FXML public TextField xTextField;
    @FXML public TextField yTextField;
    @FXML public TextField idTextField;

    private AnchorPane area;

    @FXML
    public void onApplyPointClick(ActionEvent event) {
        double x = Double.parseDouble(xTextField.getText());
        double y = Double.parseDouble(yTextField.getText());
        String id = idTextField.getText();
        Color color = colorPicker.getValue();
        Point point = Point.PointBuilder.aPoint(x,y)
                .withColor(color)
                .withId(id)
                .build();
        area.getChildren().add(point);
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        area = DrawingArea.getInstance().getArea();
        colorPicker.setValue(Color.BLACK);
        xTextField.setOnKeyReleased(key ->  {
            String txt = xTextField.getText();
            try {
                double check = Double.parseDouble(txt);
            } catch (Exception ignored){
                String backup = txt.length() >1 ? xTextField.getText().substring(0, txt.length()-1) : "";
                xTextField.setText(backup);
            }
        });

       yTextField.setOnKeyReleased(key ->  {
            String txt = yTextField.getText();
            try {
                double check = Double.parseDouble(txt);
            } catch (Exception ignored){
                String backup = txt.length() >1 ? yTextField.getText().substring(0, txt.length()-2) : "";
                yTextField.setText(backup);
            }
        });



    }
}
