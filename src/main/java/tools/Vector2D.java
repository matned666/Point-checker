package tools;

import fx.Point;

public class Vector2D {

    private Point direction;

    public Vector2D() {
    }

    public Vector2D(Point start, Point end) {
        double x = end.getX() - start.getX();
        double y = end.getY() - start.getY();
        direction = Point.PointBuilder.aPoint(x,y).build();
    }

    public static Vector2D addVectors(Vector2D v1, Vector2D v2) {

        Vector2D vector2D = new Vector2D();
        vector2D.setDirection(new Point(v1.direction.getX() + v2.direction.getX(), v1.direction.getY() + v2.direction.getY()));
        return vector2D;
    }

    public Point getDirection() {
        return direction;
    }

    public void setDirection(Point direction) {
        this.direction = direction;
    }

}
