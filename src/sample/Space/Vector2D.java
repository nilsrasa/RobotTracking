package sample.Space;

/**
 * A class that represents vectors in 2D space and can handle different vector calculations.
 * @author DFallingHammer
 * @version 1.0.0
 */
public class Vector2D {


    public static final Vector2D
            DOWN = new Vector2D(0,1),
            UP = new Vector2D(0, -1),
            LEFT = new Vector2D(-1, 0),
            RIGHT = new Vector2D(1, 0),
            ONE = new Vector2D(1,1),
            ZERO = new Vector2D(0,0);

    private float x, y;

    /**
     * Constructor
     * @param x int x component value.
     * @param y int y component value.
     */
    public Vector2D(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor
     * @param v Vector2D which will be copied.
     */
    public Vector2D(Vector2D v){
        this.x = v.getX();
        this.y = v.getY();
    }

    //Static methods:
    /**
     * Returns a copy (new instance) of the given Vector2D.
     * @param v Vector2D to be copied.
     * @return Vector2D copy.
     */
    public static Vector2D CopyOf(Vector2D v){
        Vector2D copy = new Vector2D(v);
        return copy;
    }

    /**
     * Returns the float distance between two Vector2D.
     * @param a first Vector2D
     * @param b second Vector2D
     * @return
     */
    public static float Distance(Vector2D a, Vector2D b){
        return CopyOf(a).subtract(b).getMagnitude();
    }

    /**
     * Returns a scaled Vector2D of the scaling between the Vector2D a and Vector2D b.
     * Calculated as such: "a.x *= b.x, a.y *= b.y".
     * @param a first Vector2D
     * @param b second Vector2D
     * @return The scaled Vector2D
     */
    public static Vector2D Scale(Vector2D a, Vector2D b){
        return CopyOf(a).scale(b);
    }

    /**
     * Returns a scaled Vector2D of the scaling between the Vector2D a and the scalar.
     * Calculated as such: "a.x *= scalar, a.y *= scalar".
     * @param a first Vector2D
     * @param scalar second Vector2D
     * @return The scaled Vector2D
     */
    public static Vector2D Scale(Vector2D a, float scalar){
        return CopyOf(a).scale(scalar);
    }

    /**
     * Returns a Vector2D with the lowest two components from the two Vector2D parameters.
     * @param a first Vector2D
     * @param b second Vector2D
     * @return Vector2D min
     */
    public static Vector2D Min (Vector2D a, Vector2D b) {
        float minX = (a.getX() < b.getX())? a.getX() : b.getX();
        float minY = (a.getY() < b.getY())? a.getY() : b.getY();

        return new Vector2D(minX, minY);
    }

    /**
     * Returns a Vector2D with the highest two components from the two Vector2D parameters.
     * @param a first Vector2D
     * @param b second Vector2D
     * @return Vector2D max
     */
    public static Vector2D Max (Vector2D a, Vector2D b){
        float maxX = (a.getX() > b.getX())? a.getX() : b.getX();
        float maxY = (a.getY() > b.getY())? a.getY() : b.getY();

        return new Vector2D(maxX, maxY);
    }

    /**
     * Returns the dot product of two vectors, (ax*bx + ay*by)
     * @param a first Vetor2D
     * @param b second Vector2D
     * @return float dot product
     */
    public static float DotProduct(Vector2D a, Vector2D b){
        return a.getX()*b.getX()+a.getY()*b.getY();
    }


    //Public methods:
    /**
     * Add a Vector2D to this Vector2D
     * @param v Vector2D to be added
     * @return Vector2D result
     */
    public Vector2D add(Vector2D v){
        x += v.getX();
        y += v.getY();

        return this;
    }

    /**
     * Subtract a Vector2D from this Vector2D
     * @param v Vector2D to be subtracted
     * @return Vector2D result
     */
    public Vector2D subtract(Vector2D v){
        x -= v.getX();
        y -= v.getY();

        return this;
    }

    /**
     * Scales the Vector2D by another Vector2D. Calculated as such: "this.x *= other.x, this.y *= other.y".
     * @param v The scaling Vector2D
     * @return The scaled Vector2D result
     */
    public Vector2D scale(Vector2D v){
        x *= v.getX();
        y *= v.getY();

        return this;
    }

    /**
     * Scales the Vector2D by a scalar Calculated as such: "this.x *= scalar, this.y *= scalar".
     * @param scalar The scalar
     * @return The scaled Vector2D result
     */
    public Vector2D scale(float scalar){
        x *= scalar;
        y *= scalar;

        return this;
    }

    /**
     * Clamps the vector to the given min and max bounds
     * @param min Vector2D of minimum bound
     * @param max Vector2D of maximum bound
     * @return Vector2D inside the bounds
     */
    public Vector2D clamp(Vector2D min, Vector2D max){
        if (x < min.getX())
            x = min.getX();
        else if (x > max.getX())
            x = max.getX();

        if (y < min.getY())
            y = min.getY();
        else if (y > max.getY())
            y = max.getY();

        return this;
    }


    //Getters:
    /**
     * Returns the magnitude(length) of the Vector2D.
     * @return float magnitude.
     */
    public float getMagnitude (){
        return Math.abs((float)Math.sqrt(getSqrMagnitude()));
    }

    /**
     * Returns the squared magnitude of the Vector2D. (x*x + y*y).
     * @return float squared magnitude.
     */
    public float getSqrMagnitude(){
        return (x*x + y*y);
    }

    /**
     * Returns the x component of the Vector2D.
     * @return float x component.
     */
    public float getX(){
        return x;
    }

    /**
     * Returns the y component of the Vector2D.
     * @return float y component.
     */
    public float getY(){
        return y;
    }


    //Setters:
    /**
     * Used to set the x and y components of the Vector2D.
     * @param x float new x component value.
     * @param y float new y component value.
     */
    public void set(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * Used to set the x component of the Vector2D.
     * @param x float new x component value.
     */
    public void setX(float x){
        this.x = x;
    }

    /**
     * Used to set the y component of the Vector2D.
     * @param y float new y component value.
     */
    public void setY(float y){
        this.y = y;
    }

}
