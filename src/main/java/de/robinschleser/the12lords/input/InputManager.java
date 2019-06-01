package de.robinschleser.the12lords.input;

import de.robinschleser.the12lords.input.gamepad.Gamepad;

import java.util.ArrayList;
import java.util.List;

import static de.robinschleser.the12lords.input.Interaction.InteractionType.*;
import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by Robin on 13.01.2019.
 */
public class InputManager {

    private List<Controller> controllers;

    private Interaction currentInteraction;

    public InputManager() {
        this.currentInteraction = new Interaction();
        this.controllers = new ArrayList<>();
    }

    public void handleKeyboardInput(int key, int action) {
        this.currentInteraction.setKey(key);
        this.currentInteraction.setInteractionType(KeyboardInteraction);
        if (action == GLFW_PRESS) {
            this.currentInteraction.setKeyAction(Interaction.KeyActionEnum.DOWN);
            this.currentInteraction.setKeyPessed(key);
        } else if (action == GLFW_RELEASE) {
            this.currentInteraction.setKeyAction(Interaction.KeyActionEnum.UP);
            this.currentInteraction.setKeyRelease(key);
        } else if (action == GLFW_REPEAT) {
            this.currentInteraction.setKeyAction(Interaction.KeyActionEnum.REPEATE);
        }
        for (Controller controller : controllers) {
            if (controller.isEnabled) {
                controller.interaction(this.currentInteraction);
            }
        }
    }

    public void handleMouseMove(double x, double y) {
        this.currentInteraction.setMouseX(x);
        this.currentInteraction.setMouseY(y);
        this.currentInteraction.setInteractionType(MouseInteraction);
        this.currentInteraction.setMouseAction(Interaction.MouseActionEnum.MOVE);
        for (Controller controller : controllers) {
            if (controller.isEnabled) {
                controller.interaction(this.currentInteraction);
            }
        }
    }

    public void handleMouseScroll(double xoffset, double yoffset) {
        this.currentInteraction.setOffsetX(xoffset);
        this.currentInteraction.setOffsetY(yoffset);
        this.currentInteraction.setInteractionType(MouseInteraction);
        this.currentInteraction.setMouseAction(Interaction.MouseActionEnum.SCROLL);
        for (Controller controller : controllers) {
            if (controller.isEnabled) {
                controller.interaction(this.currentInteraction);
            }
        }
    }

    public void handleMouseClick(int key, int action) {
        this.currentInteraction.setInteractionType(MouseInteraction);
        if(key == GLFW_MOUSE_BUTTON_LEFT) {
            if (action == GLFW_PRESS) {
                this.currentInteraction.setMouseAction(Interaction.MouseActionEnum.LEFT_DOWN);
            } else if (action == GLFW_RELEASE) {
                this.currentInteraction.setMouseAction(Interaction.MouseActionEnum.LEFT_UP);
            }
        }
        if(key == GLFW_MOUSE_BUTTON_RIGHT) {
            if (action == GLFW_PRESS) {
                this.currentInteraction.setMouseAction(Interaction.MouseActionEnum.RIGHT_DOWN);
            } else if (action == GLFW_RELEASE) {
                this.currentInteraction.setMouseAction(Interaction.MouseActionEnum.RIGHT_UP);
            }
        }
        if(key == GLFW_MOUSE_BUTTON_MIDDLE) {
            if (action == GLFW_PRESS) {
                this.currentInteraction.setMouseAction(Interaction.MouseActionEnum.MIDDLE_DOWN);
            } else if (action == GLFW_RELEASE) {
                this.currentInteraction.setMouseAction(Interaction.MouseActionEnum.MIDDLE_UP);
            }
        }
        for (Controller controller : controllers) {
            if (controller.isEnabled) {
                controller.interaction(this.currentInteraction);
            }
        }
    }

    public void registerController(Controller controller) {
        controllers.add(controller);
    }

    public void handleNewGamepad(Gamepad pad) {
        this.currentInteraction.addPad(pad);
    }

    public void setActivePads(int active) {
        this.currentInteraction.setActiveGamepads(active);
    }

    public void handelPadInput(Gamepad pad) {
        this.currentInteraction.setInteractionType(ControllerInteraction);
        this.currentInteraction.setLastPad(pad);

        for (Controller controller : controllers) {
            if (controller.isEnabled) {
                controller.interaction(this.currentInteraction);
            }
        }
    }

    public List<Gamepad> getPads() {
        return this.currentInteraction.getPads();
    }
}
