package sample.Space;

public class Grid {
    final float WIDTH, HEIGHT;
    Vector2D scale, offset;

    public Grid(float width, float height){
        this.WIDTH = width;
        this.HEIGHT = height;
    }

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

    public Vector2D translatePos(Vector2D pos){
        return pos
                .subtract(offset)
                .scale(scale);
    }
}
