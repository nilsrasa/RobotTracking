package sample.Objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.Space.IMovableObject;
import sample.Space.IRotatableObject;
import sample.Space.Vector2D;
import sample.View.IDrawable;

/**
 * Represents the robot
 * @author DFallingHammer
 * @version 1.0.0
 */
public class Robot extends SpaceObject implements IMovableObject, IRotatableObject, IDrawable {
    private Color color;
    private float width, height;


    @Override
    public void moveTo(Vector2D dest) {
        //TODO: implement method
    }

    @Override
    public void rotateTo(float dest) {
        //TODO: implement method
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(color);
        context.fillRect(
                position.getX()-width/2,
                position.getY()-height/2,
                width, height
        );
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color col) {
        this.color = col;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public void setWidth(float width) {
        this.width = width;
    }
}
