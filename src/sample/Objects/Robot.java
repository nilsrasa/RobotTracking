package sample.Objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.Listener.UpdateListener;
import sample.Space.IMovableObject;
import sample.Space.Vector2D;
import sample.View.IDrawable;

/**
 * Represents the robot
 * @author DFallingHammer
 * @version 1.0.1
 */
public class Robot extends SpaceObject implements IMovableObject, IDrawable, UpdateListener {
    private Color color;
    private Vector2D dest;
    private float speed;

    @Override
    public void moveTo(Vector2D dest) {
        this.dest = dest;
    }

    @Override
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(color);
        context.fillPolygon(
                new double[]{
                        position.getX()-width/2,
                        position.getX()-width/2,
                        position.getX()+width/2},
                new double[]{
                        position.getY()-height/2,
                        position.getY()+height/2,
                        position.getY()},
                3);
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
    public void OnUpdate(GraphicsContext context) {
        float m = Vector2D.Distance(position, dest);
        Vector2D newPos = new Vector2D(position.getX()/m, position.getY()/m);
        newPos.scale(speed);

        this.position = newPos;

        draw(context);
    }
}
