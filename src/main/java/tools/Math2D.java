package tools;

import fx.Point;

public class Math2D {

    public static double distanceBetweenPoints(Point point1, Point point2){
        double diffX = point1.getX() - point2.getX();
        double diffY = point1.getY() - point2.getY();
        return Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
    }

}
