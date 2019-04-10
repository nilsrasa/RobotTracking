package sample.Space;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import sample.View.IDrawable;

import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a coordinate system grid
 * @author DFallingHammer
 * @version 1.0.1
 */
public class Grid implements IDrawable {
    final float WIDTH, HEIGHT;
    final float CELLS_HOR = 45, CELLS_VER = 30, GOAL_LEFT = 14, GOAL_RIGHT = 12.5f;
    public final Vector2D CELL_SPACING;
    Vector2D scale, offset, spacing;
    Color color;
    List<IDrawable> objects = new ArrayList<>();

    public Grid(float width, float height){
        this.WIDTH = width;
        this.HEIGHT = height;
        this.CELL_SPACING = new Vector2D(
                WIDTH/CELLS_HOR,
                HEIGHT/CELLS_VER
        );
    }

    /**
     * Sets the scale of the grid compared to original points
     * @param topLeft top left corner
     * @param topRight top right corner
     * @param bottomRight bottom right corner
     * @param bottomLeft bottom left corner
     */
    public void setScale(Vector2D topLeft, Vector2D topRight, Vector2D bottomRight, Vector2D bottomLeft){
        float w, h;
        w = (topRight.getX() + bottomRight.getX() - (topLeft.getX() + bottomLeft.getX()))/2f;
        h = (bottomRight.getY() + bottomLeft.getY() - (topLeft.getY() + topRight.getY()))/2f;

        scale = new Vector2D(WIDTH/w, HEIGHT/h);
        offset = new Vector2D(
                (topLeft.getX() + bottomLeft.getX())/2f,
                (topRight.getY() + topLeft.getY())/2f
                );
    }

    /**
     * Used to translate a coordinate from original grid into this grid
     * @param pos original coordinate to be translated
     * @return Vector2D translated coordinate
     */
    public Vector2D translatePos(Vector2D pos){
        pos = Vector2D.CopyOf(pos)
                .subtract(offset) //Offset the point
                .scale(scale) //Scale to actual grid size
                .clamp(new Vector2D(0,0), new Vector2D(WIDTH, HEIGHT)); //Clamp inside grid bounds
        return pos;
    }

    public void addObject(IDrawable object){
        objects.add(object);
    }

    @Override
    public void draw(GraphicsContext context) {
        //Draw grid
        context.setStroke(Paint.valueOf(color.toString()));
        context.setLineWidth(2);
        //Draws the vertical grid-lines
        for (int i = 1; i < CELLS_HOR; i++){
            context.strokeLine(i*CELL_SPACING.getX(), 0, i*CELL_SPACING.getX(), HEIGHT);
        }

        //Draws the horizontal grid-lines
        for (int i = 1; i < CELLS_VER; i++){
            context.strokeLine(0, i*CELL_SPACING.getY(), WIDTH, i*CELL_SPACING.getY());
        }
        //Draw outline
        context.setStroke(Paint.valueOf(Color.RED.toString()));
        context.strokeLine(0,0,WIDTH,0); //Top
        context.strokeLine(0,HEIGHT, WIDTH, HEIGHT); //Bottom
        context.strokeLine(0,0,0, CELL_SPACING.getY()*GOAL_LEFT); //Left top
        context.strokeLine(0, HEIGHT, 0, HEIGHT-CELL_SPACING.getY()*GOAL_LEFT);//Left bottom
        context.strokeLine(WIDTH,0,WIDTH, CELL_SPACING.getY()*GOAL_RIGHT); //Right top
        context.strokeLine(WIDTH, HEIGHT, WIDTH, HEIGHT-CELL_SPACING.getY()*GOAL_RIGHT);//Right bottom

        //Draw children
        for(IDrawable obj : objects){
            obj.draw(context);
        }
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color col) {
        this.color = col;
    }

    public Vector2D getCenterPos(){
        return new Vector2D(WIDTH/2,HEIGHT/2);
    }
}
