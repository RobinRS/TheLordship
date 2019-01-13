package de.robinschleser.the12lords.input;

import java.util.ArrayList;

/**
 * Created by Robin on 13.01.2019.
 */
public class InputManager {

    private ArrayList<KeyController> keyControllers;
    private ArrayList<MouseController> mouseControllers;

    public InputManager() {
        this.keyControllers = new ArrayList<>();
        this.mouseControllers = new ArrayList<>();
    }

    public ArrayList<KeyController> getKeyControllers() {
        return keyControllers;
    }

    public ArrayList<MouseController> getMouseControllers() {
        return mouseControllers;
    }

    public void handleInput() {
        for (KeyController keyController : keyControllers) {
            if(keyController.isEnabled()) {

            }
        }
        for (MouseController mouseController : mouseControllers) {
            if(mouseController.isEnabled()) {

            }
        }
    }

}
