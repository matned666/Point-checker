package fx;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;

public class DecorationLine extends Line implements Decoration{

    private final Point start;
    private final Point end;

    public DecorationLine(Point start, Point end) {
        this.start = start;
        this.end = end;
        setLocation();
    }

    public void setLocation(){
        double startX = start.getX();
        double startY = start.getY();
        double endX = end.getX();
        double endY = end.getY();
        StackPane drawingPane = DrawingArea.getInstance().getArea();
        double middleX = drawingPane.getWidth() / 2;
        double middleY = drawingPane.getHeight() / 2;
        setStartX(0);
        setStartY(0);
        double deltaX = endX - startX;
        double deltaY = endY - startY;
        setEndX(deltaX);
        setEndY(deltaY);
        double translationX = startX - middleX + (endX - startX) / 2;
        double translationY = startY - middleY + (endY - startY) / 2;
        setTranslateX(translationX);
        setTranslateY(translationY);
    }

    public void update() {
        setLocation();
    }
}
