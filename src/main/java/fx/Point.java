package fx;

import io.reactivex.subjects.PublishSubject;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import tools.Vector2D;

import java.util.LinkedList;
import java.util.List;

public class Point extends Circle implements Decoration{

    private String myId;
    private double x;
    private double y;
    private Color color;
    private Label label;
    private final fx.DrawingArea drawingArea = fx.DrawingArea.getInstance();
    private List<Decoration> dependentDecorations;

    private final PublishSubject<Point> changeObservable = PublishSubject.create();

    private Point() {
        dependentDecorations = new LinkedList<>();
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    Point(Point copy){
        myId = copy.myId;
        x = copy.x;
        y = copy.y;
        color = copy.color;
        dependentDecorations = new LinkedList<>();
    }

    public void subscribe(Decoration decor){
        dependentDecorations.add(decor);
        changeObservable.subscribe(observable -> decor.update());
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

    public void removeDecorations(){
        dependentDecorations.forEach(decor -> {
            ((Node)decor).setVisible(false);
            drawingArea.getArea().getChildren().remove(decor);
            dependentDecorations.remove(decor);
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

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
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

    @Override
    public void update() {
        setCenterX(x  - drawingArea.getArea().getWidth() / 2);
        setCenterY(y  - drawingArea.getArea().getHeight() / 2);
        setTranslateX(x  - drawingArea.getArea().getWidth() / 2);
        setTranslateY(y  - drawingArea.getArea().getHeight() / 2);
        if (label != null) {
            label.setTranslateX(x - drawingArea.getArea().getWidth() / 2);
            label.setTranslateY(y - drawingArea.getArea().getHeight() / 2 - getRadius() - label.getHeight() / 2);
        }
    }

    public void move(Vector2D direction, double distance){
        double u1 = direction.getDirection().x;
        double u2 = direction.getDirection().y;
        double tDenominator = Math.sqrt(Math.pow(u1, 2) + Math.pow(u2, 2));
        double t = distance / tDenominator;
        x += u1*t;
        y += u2*t;
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
