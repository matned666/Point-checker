package fx;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import measurement.MeasurementType;

import java.util.ArrayList;
import java.util.List;

public class DrawingArea {

    private static DrawingArea instance;

    public static DrawingArea getInstance() {
        if (instance == null) {
            instance = new DrawingArea();
        }
        return instance;
    }

    private boolean initialized = false;

    private final List<Point> points;

    private Point actuallySelected;

    private StackPane area;
    private Label pointX;
    private Label pointY;
    private Label translateX;
    private Label translateY;
    private Label centerX;
    private Label centerY;
    private Label pointId;
    private Label height;
    private Label width;
    private MeasurementType actualMeasurement = MeasurementType.NONE;

    public DrawingArea() {
        points = new ArrayList<>();
    }

    public void init(StackPane area, Label pointX, Label pointY, Label translateX, Label translateY, Label centerX,
            Label centerY, Label pointId, Label height, Label width) {
        initialized = true;
        this.area = area;
        this.pointX = pointX;
        this.pointY = pointY;
        this.translateX = translateX;
        this.translateY = translateY;
        this.centerX = centerX;
        this.centerY = centerY;
        this.pointId = pointId;
        this.height = height;
        this.width = width;
    }

    public Point getActuallySelected() {
        if (possibleToMakeLine()) {
            return points.get(points.size() - 1);
        } else {
            return null;
        }
    }

    public boolean possibleToMakeLine() {
        return points.size() > 0;
    }

    public Point previousActuallySelected() {
        if (possibleToMakeAngle()) {
            return points.get(points.size() - 2);
        } else {
            return null;
        }
    }

    public boolean possibleToMakeAngle() {
        return points.size() > 1;
    }

    public void removeActuallySelected() {
        if (initialized && actuallySelected != null) {
            points.remove(actuallySelected);
            actuallySelected.removeDecorations();
            area.getChildren().remove(actuallySelected);
            area.getChildren().remove(actuallySelected.getLabel());
            actuallySelected.setManaged(false);
            actuallySelected.setVisible(false);
            actuallySelected.getLabel().setVisible(false);
            if (possibleToMakeLine()) {
                actuallySelected = points.get(points.size() - 1);
                actuallySelected.setStroke(Color.RED);
            } else {
                actuallySelected = null;
            }
            update();
        }
    }

    public void addPoint(Point actuallySelected) {
        area.getChildren().add(actuallySelected.getLabel());
        area.getChildren().add(actuallySelected);
        actuallySelected.getLabel().setTranslateX(actuallySelected.getTranslateX());
        actuallySelected.getLabel().setTranslateY(actuallySelected.getTranslateY()-10);
        points.add(actuallySelected);
        setActuallySelected(actuallySelected);
    }

    public MeasurementType getActualMeasurement() {
        return actualMeasurement;
    }

    public void setActualMeasurement(MeasurementType actualMeasurement) {
        this.actualMeasurement = actualMeasurement;
    }

    public void setActuallySelected(Point actuallySelected) {
        if (initialized) {
            points.forEach(p -> {
                p.setStroke(Color.TRANSPARENT);
                p.getLabel().setTextFill(p.getColor());

            });
            this.actuallySelected = actuallySelected;
            points.remove(actuallySelected);
            points.add(actuallySelected);
            this.actuallySelected.setStroke(Color.RED);
            this.actuallySelected.getLabel().setTextFill(Color.RED);
        }
    }

    public StackPane getArea() {
        return area;
    }

    public void update() {
        if (initialized) {
            pointId.setText(actuallySelected != null ? String.valueOf(this.actuallySelected.getMyId()) : null);
            pointX.setText(actuallySelected != null ? String.valueOf(this.actuallySelected.getX()) : null);
            pointY.setText(actuallySelected != null ? String.valueOf(this.actuallySelected.getY()) : null);
            translateX.setText(actuallySelected != null ? String.valueOf(this.actuallySelected.getTranslateX()) : null);
            translateY.setText(actuallySelected != null ? String.valueOf(this.actuallySelected.getTranslateY()) : null);
            centerX.setText(actuallySelected != null ? String.valueOf(this.actuallySelected.getCenterX()) : null);
            centerY.setText(actuallySelected != null ? String.valueOf(this.actuallySelected.getCenterY()) : null);
            height.setText(String.valueOf(area.getHeight()));
            width.setText(String.valueOf(area.getWidth()));
        }
    }

    public void showDecoration(Node line) {
        line.setVisible(true);
        area.getChildren().add(line);
    }

    public void clearArea() {

    }
}
