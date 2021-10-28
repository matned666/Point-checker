package fx;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

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

    private AnchorPane area;
    private Label pointX;
    private Label pointY;
    private Label pointId;

    public DrawingArea() {
        points = new ArrayList<>();
    }

    public void init(AnchorPane area, Label pointX, Label pointY, Label pointId) {
        initialized = true;
        this.area = area;
        this.pointX = pointX;
        this.pointY = pointY;
        this.pointId = pointId;
    }

    public AnchorPane getArea() {
        return area;
    }

    public Point getActuallySelected() {
        return actuallySelected;
    }

    public void removeActuallySelected(){
        if (initialized) {
            points.remove(actuallySelected);
            actuallySelected.setManaged(false);
            actuallySelected.setVisible(false);
            if (points.size() > 0) {
                actuallySelected = points.get(points.size() - 1);
            } else {
                actuallySelected = null;
            }
            updateLabels();
        }
    }

    public void addPoint(Point actuallySelected){
        points.add(actuallySelected);
        setActuallySelected(actuallySelected);
    }

    public void setActuallySelected(Point actuallySelected) {
        if (initialized) {
            this.actuallySelected = actuallySelected;
            updateLabels();
        }
    }

    public void updateLabels() {
        if (initialized) {
            pointId.setText(String.valueOf(this.actuallySelected.getMyId()));
            pointX.setText(String.valueOf(this.actuallySelected.getX()));
            pointY.setText(String.valueOf(this.actuallySelected.getY()));
        }
    }
}
