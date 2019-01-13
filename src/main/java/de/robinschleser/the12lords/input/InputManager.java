package de.robinschleser.the12lords.input;

import org.lwjgl.BufferUtils;
import org.w3c.dom.events.MouseEvent;

import java.nio.DoubleBuffer;
import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by Robin on 13.01.2019.
 */
public class InputManager {

    private long window;
    private ArrayList<KeyController> keyControllers;
    private ArrayList<MouseController> mouseControllers;

    public InputManager(long window) {
        this.window = window;
        this.keyControllers = new ArrayList<>();
        this.mouseControllers = new ArrayList<>();
    }

    public ArrayList<KeyController> getKeyControllers() {
        return keyControllers;
    }

    public ArrayList<MouseController> getMouseControllers() {
        return mouseControllers;
    }

    public void handleKeyboardInput(int key, int action) {
        for (KeyController keyController : keyControllers) {
            if(keyController.isKeyBoardEnabled()) {
                if ( action == GLFW_PRESS ) {
                    keyController.keyPress(key);
                } else if ( action == GLFW_RELEASE ) {
                    keyController.keyRelease(key);
                } else if ( action == GLFW_REPEAT ) {
                    keyController.keyRepeat(key);
                }
            }
        }
    }

    public void handleMouseMove(double x, double y) {
        for (MouseController mouseController : mouseControllers) {
            if(mouseController.isMouseEnabled()) {
                mouseController.mouseMovment((int)x, (int)y);
            }
        }
    }

    public void handleMouseScroll(double xoffset, double yoffset) {
        for (MouseController mouseController : mouseControllers) {
            if(mouseController.isMouseEnabled()) {
                mouseController.mouseScroll(xoffset, yoffset);
            }
        }
    }

    public void handleMouseClick(int key, int action) {
        for (MouseController mouseController : mouseControllers) {
            if(mouseController.isMouseEnabled()) {
                if(key == GLFW_MOUSE_BUTTON_LEFT) {
                    if ( action == GLFW_PRESS ) {
                        mouseController.mouseLeftDown();
                    } else if ( action == GLFW_RELEASE ) {
                        mouseController.mouseLeftUp();
                    } else if ( action == GLFW_REPEAT ) {
                        mouseController.mouseLeftRepeat();
                    }
                }
                if(key == GLFW_MOUSE_BUTTON_MIDDLE) {
                    if ( action == GLFW_PRESS ) {
                        mouseController.mouseMiddleDown();
                    } else if ( action == GLFW_RELEASE ) {
                        mouseController.mouseMiddleUp();
                    } else if ( action == GLFW_REPEAT ) {
                        mouseController.mouseMiddleRepeat();
                    }
                }
                if(key == GLFW_MOUSE_BUTTON_RIGHT) {
                    if ( action == GLFW_PRESS ) {
                        mouseController.mouseRightDown();
                    } else if ( action == GLFW_RELEASE ) {
                        mouseController.mouseRightUp();
                    } else if ( action == GLFW_REPEAT ) {
                        mouseController.mouseRightRepeat();
                    }
                }
            }
        }
    }

    public void registerKeyboardController(KeyController controller) {
        keyControllers.add(controller);
    }

    public void registerMouseController(MouseController controller) {
        mouseControllers.add(controller);
    }

}
