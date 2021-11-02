package fx;

import io.reactivex.subjects.PublishSubject;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Point extends Circle implements Decoration{

    private String myId;
    private double x;
    private double y;
    private Color color;
    private final fx.DrawingArea drawingArea = fx.DrawingArea.getInstance();
    private Label label;

    private PublishSubject<Point> changeObservable = PublishSubject.create();

    private Point() {
    }

    private Point(Point main){
        if (main != null){
            changeObservable.subscribe(observable -> {
                main.x = x;
                main.update();
            });
        }
    }

    public void subscribeLine(DecorationLine line){
        changeObservable.subscribe(observable -> line.update());
    }

    private void init(){
        double radius = 3;
        label = new Label(myId);
        label.setVisible(true);
        setRadius(radius);
        setFill(color);
        setStrokeWidth(2);
        setVisible(true);
        setOnMousePressed(click -> {
            fx.DrawingArea.getInstance().setActuallySelected(this);
            fx.DrawingArea.getInstance().update();
        });
        setOnMouseDragged(mouse -> {
            x = mouse.getX() + drawingArea.getArea().getWidth() / 2;
            y = mouse.getY() + drawingArea.getArea().getHeight() / 2;
            update();
            changeObservable.onNext(this);
            fx.DrawingArea.getInstance().update();
        });

    }

    public String getMyId() {
        return myId;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Label getLabel() {
        return label;
    }

    public Color getColor() {
        return color;
    }

    @Override public String toString() {
        return "Point{" + "myId='" + myId + '\'' + ", x=" + x + ", y=" + y + ", color=" + color + '}';
    }

    public void update() {
        setCenterX(x  - drawingArea.getArea().getWidth() / 2);
        setCenterY(y  - drawingArea.getArea().getHeight() / 2);
        setTranslateX(x  - drawingArea.getArea().getWidth() / 2);
        setTranslateY(y  - drawingArea.getArea().getHeight() / 2);
        label.setTranslateX(x - drawingArea.getArea().getWidth() / 2);
        label.setTranslateY(y - drawingArea.getArea().getHeight() / 2 - getRadius() - label.getHeight()/2);
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

        public Point build(Point main) {
            Point point = new Point(main);
            point.myId = this.id;
            point.x = this.x;
            point.y = this.y;
            point.color = this.color;
            point.init();
            return point;
        }




    }
}
