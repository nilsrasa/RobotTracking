package sample.Space;

/**
 * Represents a coordinate system grid
 * @author DFallingHammer
 * @version 1.0.0
 */
public class Grid {
    final float WIDTH, HEIGHT;
    Vector2D scale, offset;

    public Grid(float width, float height){
        this.WIDTH = width;
        this.HEIGHT = height;
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
}
