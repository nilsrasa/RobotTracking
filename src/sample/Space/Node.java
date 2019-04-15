package sample.Space;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Each node correspond to a cell from the grid.
 * They are used for pathfinding.
 * @author DFallingHammer
 * @version 1.0.0
 */
public class Node {
    final Vector2D position;

    public Node (float x, float y){
        this.position = new Vector2D(x,y);
    }

    /**
     * Debugging method to print out the coordinates
     * @param i
     * @param j
     */
    public void print(int i, int j){
        System.out.println("Node ["+i+", "+j+"] at pos ("+position.getX()+", "+position.getY()+");");
    }

    /**
     * Debuggin method to show the node as a point on the grid
     * @param c
     */
    public void draw(GraphicsContext c){
        c.setFill(Color.CORAL);
        c.fillOval(
                position.getX()-1,
                position.getY()-1,
                2, 2
        );
    }
}
