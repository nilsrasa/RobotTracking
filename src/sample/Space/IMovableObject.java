package sample.Space;

/**
 * Interface for a movable object in 2d space.
 * @author DFallingHammer
 * @version 1.0.0
 */
public interface IMovableObject {

    /**
     * Moves the x,y postion, over time, to the given Vector2D position
     * @param dest the Vector2D destination
     */
    void moveTo(Vector2D dest);
}
