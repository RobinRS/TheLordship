package de.robinschleser.the12lords.input;

/**
 * Created by Robin on 13.01.2019.
 */
public interface MouseController {

    boolean isEnabled();

    void mouseMovment(int x, int y);

    void mouseLeftPress();
    void mouseLeftDown();

    void mouseRightPress();
    void mouseRightDown();

}
