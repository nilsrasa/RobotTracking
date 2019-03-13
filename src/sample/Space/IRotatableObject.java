package sample.Space;

/**
 * Interface for a rotatable object in 2d space
 * @author DFallingHammer
 * @version 1.0.0
 */
public interface IRotatableObject {

    /**
     * Rotates the object, over time, to the new rotation
     * @param tar the target rotation
     */
    void rotateTo(float tar);

}
