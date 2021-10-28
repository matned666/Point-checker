package fx;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class DrawingArea {

    private static DrawingArea instance;

    public static DrawingArea getInstance(){
        if (instance == null){
            instance = new DrawingArea();
        }
        return instance;
    }

    private AnchorPane area;
    public Label pointX;
    public Label pointY;
    public Label pointTranslateX;
    public Label pointTranslateY;
    public Label pointId;

    public DrawingArea() {
    }

    public AnchorPane getArea() {
        return area;
    }

    public void setArea(AnchorPane area) {
        this.area = area;
    }

    public Label getPointX() {
        return pointX;
    }

    public void setPointX(Label pointX) {
        this.pointX = pointX;
    }

    public Label getPointY() {
        return pointY;
    }

    public void setPointY(Label pointY) {
        this.pointY = pointY;
    }

    public Label getPointTranslateX() {
        return pointTranslateX;
    }

    public void setPointTranslateX(Label pointTranslateX) {
        this.pointTranslateX = pointTranslateX;
    }

    public Label getPointTranslateY() {
        return pointTranslateY;
    }

    public void setPointTranslateY(Label pointTranslateY) {
        this.pointTranslateY = pointTranslateY;
    }

    public Label getPointId() {
        return pointId;
    }

    public void setPointId(Label pointId) {
        this.pointId = pointId;
    }
}
