package measurement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum MeasurementType {

    NONE(1),
    COBB(4);

    public static ObservableList<String> items(){
        return FXCollections.observableArrayList(Arrays.stream(values()).map(Enum::name).collect(Collectors.toList()));
    }

    private final int numberOfPoints;

    MeasurementType(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }
}
