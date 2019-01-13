package de.robinschleser.the12lords.input;

/**
 * Created by Robin on 13.01.2019.
 */
public interface MouseController {

    boolean isMouseEnabled();

    void mouseMovment(int x, int y);
    void mouseScroll(double xoffset, double yoffset);

    void mouseLeftUp();
    void mouseLeftDown();
    void mouseLeftRepeat();

    void mouseRightUp();
    void mouseRightDown();
    void mouseRightRepeat();

    void mouseMiddleUp();
    void mouseMiddleDown();
    void mouseMiddleRepeat();

}
