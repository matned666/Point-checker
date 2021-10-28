package fx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Point extends Circle {

    private String myId;
    private double x;
    private double y;
    private Color color;

    private Point() {
    }

    private void init(){
        setCenterX(x);
        setCenterY(y);
        double radius = 3;
        setRadius(radius);
        setFill(color);
        setVisible(true);
        setOnMouseClicked(click -> {
            DrawingArea drawingArea = DrawingArea.getInstance();
            drawingArea.getPointId().setText(String.valueOf(myId));
            drawingArea.getPointX().setText(String.valueOf(x));
            drawingArea.getPointY().setText(String.valueOf(y));
        });
    }

    public static final class PointBuilder {
        private String id;
        private final double x;
        private final double y;
        private Color color;

        private PointBuilder(double x, double y) {
            this.x = x;
            this.y = y;
            id = "";
            color = Color.BLACK;
        }

        public static PointBuilder aPoint(double x, double y) {
            return new PointBuilder(x,y);
        }

        public PointBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public PointBuilder withColor(Color color) {
            this.color = color;
            return this;
        }

        public Point build() {
            Point point = new Point();
            point.myId = this.id;
            point.x = this.x;
            point.y = this.y;
            point.color = this.color;
            point.init();
            return point;
        }
    }
}