package fx;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

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

    public DrawingArea() {
        points = new ArrayList<>();
    }

    public void init(StackPane area, Label pointX, Label pointY, Label translateX, Label translateY, Label centerX,
            Label centerY, Label pointId) {
        initialized = true;
        this.area = area;
        this.pointX = pointX;
        this.pointY = pointY;
        this.translateX = translateX;
        this.translateY = translateY;
        this.centerX = centerX;
        this.centerY = centerY;
        this.pointId = pointId;
    }

    public Point getActuallySelected() {
        return actuallySelected;
    }

    public void removeActuallySelected() {
        if (initialized && actuallySelected != null) {
            points.remove(actuallySelected);
            actuallySelected.setManaged(false);
            actuallySelected.setVisible(false);
            if (points.size() > 0) {
                actuallySelected = points.get(points.size() - 1);
                actuallySelected.setStroke(Color.RED);
            } else {
                actuallySelected = null;
            }
            update();
        }
    }

    public void addPoint(Point actuallySelected) {
        area.getChildren().add(actuallySelected);
        points.add(actuallySelected);
        setActuallySelected(actuallySelected);
    }

    public void setActuallySelected(Point actuallySelected) {
        if (initialized) {
            points.forEach(p -> p.setStroke(Color.TRANSPARENT));
            this.actuallySelected = actuallySelected;
            this.actuallySelected.setStroke(Color.RED);
        }
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
        }
    }
}
