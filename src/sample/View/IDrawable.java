package sample.View;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * JavaFX drawable object
 * @author DFallingHammer
 * @version 1.0.0
 */
public interface IDrawable {

    /**
     * Draws the object in the given Group
     */
    void draw(GraphicsContext context);

    /**
     * Returns the javaFx color of the drawable
     * @return Color
     */
    Color getColor();

    /**
     * Sets the javaFx color of the drawable
     * @param col the new Color
     */
    void setColor(Color col);

    /**
     * Returns the float height
     * @return float height
     */
    float getHeight();

    /**
     * Sets the float height
     * @param height the new height
     */
    void setHeight(float height);

    /**
     * Returns the float width
     * @return float width
     */
    float getWidth();

    /**
     * Sets the float width
     * @param width the new width
     */
    void setWidth(float width);

}
