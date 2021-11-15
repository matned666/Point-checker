package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class SaveController implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private VBox measurementsVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> list = Arrays.asList("PR file", "Workspace", "Zapisz jako HP (Poziom serii)", "Zapisz jako HP (Poziom badań)", "WL", "Rotacja", "Powiększenie", "Kolejność obrazów w serii", "Stan widoku");
        choiceBox.getItems().addAll(list);
        choiceBox.setValue(list.get(0));
    }
}
