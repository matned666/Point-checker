package fx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import tools.Math2D;
import tools.Vector2D;

public class AngleCurve extends Arc implements Decoration {

    private Point anglePoint1;
    private Point anglePoint2;

    private double startAngle;
    private double lengthAngle;
    private double radius;

    private final Point tip;
    private final Point arm1;
    private final Point arm2;

    public AngleCurve(Point tip, Point arm1, Point arm2) {
        this.tip = tip;
        this.arm1 = arm1;
        this.arm2 = arm2;
        setFill(Color.TRANSPARENT);
        setStroke(tip.getColor());
        update();
    }

    @Override
    public void update() {
        double distance1 = Math2D.distanceBetweenPoints(tip, arm1);
        double distance2 = Math2D.distanceBetweenPoints(tip, arm2);
        radius = Math.max(30, Math.min(distance1, distance2) / 3);
        anglePoint1 = new Point(tip);
        anglePoint2 = new Point(tip);
        Vector2D anglePoint1Vector = new Vector2D(tip, arm1);
        Vector2D anglePoint2Vector = new Vector2D(tip, arm2);
        anglePoint1.move(anglePoint1Vector, radius);
        anglePoint2.move(anglePoint2Vector, radius);
        getStrokeDashArray().addAll(25d, 10d);
        updateInner(true);
    }

    private void updateInner(boolean b) {
        Point originPoint = new Point(tip);
        originPoint.setX(tip.getX() + radius);
        if (b) {
            startAngle = Math2D.countAngle(tip, originPoint, anglePoint1);
            lengthAngle = Math2D.countAngle(tip, anglePoint1, anglePoint2);
            if (lengthAngle > 180) {
                updateInner(false);
            }else {
                setLocation();
            }
        } else {
            startAngle = Math2D.countAngle(tip, originPoint, anglePoint2);
            lengthAngle = Math2D.countAngle(tip, anglePoint2, anglePoint1);
            setLocation();
        }
    }

    public void setLocation() {
        Point translationPoint = new Point(tip);
        Vector2D v1 = new Vector2D(tip, anglePoint1);
        Vector2D v2 = new Vector2D(tip, anglePoint2);
        Vector2D translationPointMoveVector = Vector2D.addVectors(v1, v2);
        double mod = lengthAngle / 68;
        translationPoint.move(translationPointMoveVector,radius/ Math.max(mod, 1));
        translationPoint.update();


        setLayoutX(tip.getX());
        setLayoutY(tip.getY());
        setRadiusX(radius);
        setRadiusY(radius);
        setStartAngle(startAngle);
        setLength(lengthAngle);

        System.out.println("angle: " + lengthAngle);
        System.out.println("distance: " + Math2D.distanceBetweenPoints(tip, anglePoint1));
        System.out.println("radius: " + radius);
        System.out.println("angle2:" + Math2D.countAngle(tip, anglePoint1, anglePoint2));
        System.out.println("tip coords X:" + tip.getX() + ", Y:" + tip.getY());
        System.out.println();

        double translationX = translationPoint.getTranslateX();
        double translationY = translationPoint.getTranslateY();
        setCenterX(translationX);
        setCenterY(translationY);
        setTranslateX(translationX);
        setTranslateY(translationY);
    }

}
