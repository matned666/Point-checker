package measurement;

public class Cobb implements ViewMeasurement {

    private final MeasurementType measurementType;

    public Cobb() {
        this.measurementType = MeasurementType.COBB;
    }

    public MeasurementType getMeasurementType() {
        return measurementType;
    }
}
