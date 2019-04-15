package sample.Objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.View.IDrawable;

public class Mål extends SpaceObject implements IDrawable {
    Color color;


    @Override
    public void draw(GraphicsContext context) {
        context.setStroke(color);
        context.setLineWidth(width);
        context.strokeLine(position.getX(),position.getY()-height/2,position.getX(),position.getY()+height/2);
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color col) {
        this.color = col;
    }
}
