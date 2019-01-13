package de.robinschleser.the12lords.input;

/**
 * Created by Robin on 13.01.2019.
 */
public interface KeyController {

    boolean isEnabled();
    void keyDown();
    void keyUp();
    void keyPress();

}
