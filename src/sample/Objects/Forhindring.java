package sample.Objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import sample.Space.Vector2D;
import sample.View.IDrawable;

/**
 * Represents an obstacle
 * @author DFallingHammer
 * @version 1.0.0
 */
public class Forhindring extends SpaceObject implements IDrawable {
    private Color color;
    private Vector2D start, end;

    @Override
    public void draw(GraphicsContext context) {
        context.setStroke(Paint.valueOf(color.toString()));
        context.setLineWidth(width);
        context.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color col) {
        this.color = col;
    }

    public void setPoints(Vector2D start, Vector2D end){
        this.start = start;
        this.end = end;
    }
}
