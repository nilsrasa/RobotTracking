package sample.Objects;

import sample.Space.Vector2D;

/**
 * Represents an object in 2d space
 * @author DFallingHammer
 * @version 1.0.0
 */
public abstract class SpaceObject {
    protected Vector2D position;
    protected float rotation;

    /**
     * Returns the current x,y position as a Vector2D
     * @return Vector2D of current position
     */
    public Vector2D getPos() {
        return Vector2D.CopyOf(this.position);
    }

    /**
     * Sets the x,y positon to the given Vector2D
     * @param pos the new Vector2D position
     */
    public void setPos(Vector2D pos) {
        this.position = pos;
    }

    /**
     * Returns the current rotation as a float value
     * @return float the rotation
     */
    public float getRotation() {
        return this.rotation;
    }

    /**
     * Set the rotation to the given rotation
     * @param rot the new rotation
     */
    public void setRotation(float rot) {
        this.rotation = rot;
    }

}
