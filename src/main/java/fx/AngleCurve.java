package fx;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.CubicCurve;
import tools.Math2D;

import java.util.Vector;

public class AngleCurve extends CubicCurve implements Decoration{

    private Point anglePoint1;
    private Point anglePoint2;
    private Point controlPoint1;
    private Point controlPoint2;

    private Point tip;
    private Point arm1;
    private Point arm2;

    public AngleCurve(Point tip, Point arm1, Point arm2) {
        this.tip = tip;
        this.arm1 = arm1;
        this.arm2 = arm2;
        update();
    }

    private void update() {
        double distance1 = Math2D.distanceBetweenPoints(tip, anglePoint1);
        double distance2 = Math2D.distanceBetweenPoints(tip, anglePoint2);
        double angleDistance = (distance1 + distance2)/2;



    }

    public void setLocation(){
        double startX = anglePoint1.getX();
        double startY = anglePoint1.getY();
        double control1X = controlPoint1.getX();
        double control1Y = controlPoint1.getY();
        double control2X = controlPoint2.getX();
        double control2Y = controlPoint2.getY();
        double endX = anglePoint2.getX();
        double endY = anglePoint2.getY();


        StackPane drawingPane = DrawingArea.getInstance().getArea();
        double middleX = drawingPane.getWidth() / 2;
        double middleY = drawingPane.getHeight() / 2;
        setStartX(0);
        setStartY(0);
        double deltaControl1X = control1X - startX;
        double deltaControl1Y = control1Y - startY;
        setControlX1(deltaControl1X);
        setControlY1(deltaControl1Y);
        double deltaControl2X = control2X - startX;
        double deltaControl2Y = control2Y - startY;
        setControlX2(deltaControl2X);
        setControlY2(deltaControl2Y);
        double deltaX = endX - startX;
        double deltaY = endY - startY;
        setEndX(deltaX);
        setEndY(deltaY);
        double translationX = startX - middleX + (endX - startX) / 2;
        double translationY = startY - middleY + (endY - startY) / 2;
        setTranslateX(translationX);
        setTranslateY(translationY);
    }

}
