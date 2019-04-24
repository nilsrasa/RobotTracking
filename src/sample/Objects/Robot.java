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
    private SpaceObject target;

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
        context.save();
        context.translate(position.getX(), position.getY());
        context.rotate(rotation);
        context.translate(-position.getX(), -position.getY());

        context.setFill(color);
        context.fillRect(position.getX()-width/2,position.getY()-height/2, width, height);
        context.setStroke(color.invert());
        context.setLineWidth(height/4);
        context.strokeLine(position.getX(),position.getY(),position.getX()+width/2,position.getY());
        /*context.fillPolygon(
                new double[]{
                        position.getX()-width/2,
                        position.getX()-width/2,
                        position.getX()+width/2},
                new double[]{
                        position.getY()-height/2,
                        position.getY()+height/2,
                        position.getY()},
                3);*/

        context.restore();
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color col) {
        this.color = col;
    }

    public void setTarget(SpaceObject target){
        this.target = target;
        turnToward(target.position);
    }

    public SpaceObject getTarget(){
        return target;
    }

    public void turnToward(Vector2D point){
        Vector2D direction = Vector2D.CopyOf(point).subtract(position);

        Vector2D v;
        int d;
        if (point.getY() < position.getY()){
            v = Vector2D.LEFT;
            d = 180;
        }
        else {
            v = Vector2D.RIGHT;
            d = 0;
        }

        float cos0 = Vector2D.DotProduct(v,direction) /
                (v.getMagnitude() * direction.getMagnitude());

        rotation = (float)Math.toDegrees(Math.acos(cos0))+d;
        System.out.println(rotation);
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
