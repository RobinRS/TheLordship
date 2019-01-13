package de.robinschleser.the12lords.input;

/**
 * Created by Robin on 13.01.2019.
 */
public interface KeyController {

    boolean isKeyBoardEnabled();
    void keyPress(int key);
    void keyRelease(int key);
    void keyRepeat(int key);

}
