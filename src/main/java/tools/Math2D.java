package tools;

import fx.Point;

public class Math2D {

    public static double distanceBetweenPoints(Point point1, Point point2){
        double diffX = point1.getX() - point2.getX();
        double diffY = point1.getY() - point2.getY();
        return Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
    }

    public static double countAngle(Point tip, Point anglePoint1, Point anglePoint2) {
        double angle1 = Math.atan2(anglePoint2.getY() - tip.getY(), tip.getX() - anglePoint2.getX());
        double angle2 = Math.atan2(anglePoint1.getY() - tip.getY(), tip.getX() - anglePoint1.getX());
        double calculatedAngle = Math.toDegrees(angle1 - angle2);
        if (calculatedAngle < 0)
            calculatedAngle += 360;
        return calculatedAngle;
    }
}
