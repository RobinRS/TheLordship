package de.robinschleser.the12lords.entity;

import de.robinschleser.the12lords.input.Interaction;
import de.robinschleser.the12lords.input.gamepad.GamePadButton;
import de.robinschleser.the12lords.input.gamepad.Gamepad;

import java.util.UUID;

import static de.robinschleser.the12lords.input.Interaction.InteractionType.ControllerInteraction;
import static de.robinschleser.the12lords.input.Interaction.InteractionType.KeyboardInteraction;
import static org.lwjgl.glfw.GLFW.*;

public class PlayerEntity extends MovingEntity {

    public PlayerEntity(UUID uuid, String name) {
        super(uuid, name);
        setCanMove(true);
    }

    @Override
    public void interaction(Interaction action) {
        if(KeyboardInteraction == action.getInteractionType()) {
            if(action.getKey() == GLFW_KEY_0) {
                if(action.getKeyAction() == Interaction.KeyActionEnum.DOWN) {
                    initDebugEntity();
                    this.getMesh().setShouldBeRendert(true);
                }
            }
            if(action.getKey() == GLFW_KEY_UP) {
                this.getLocation().setY(this.getLocation().getY() + 1);
            }

            if(action.getKey() == GLFW_KEY_DOWN) {
                this.getLocation().setY(this.getLocation().getY() - 1);
            }

            if(action.getKey() == GLFW_KEY_LEFT && action.getKeysDown().contains(GLFW_KEY_LEFT_CONTROL)) {
                this.getLocation().setX(this.getLocation().getX() - 1);
            }

            if(action.getKey() == GLFW_KEY_RIGHT && action.getKeysDown().contains(GLFW_KEY_LEFT_CONTROL)) {
                this.getLocation().setX(this.getLocation().getX() + 1);
            }
        }

        if(action.getInteractionType() == ControllerInteraction) {
            Gamepad pad = action.getLastPad();

            if(pad.getButtonsDown().contains(GamePadButton.PAD_DOWN)) {
                this.getLocation().setY(this.getLocation().getY() - 1);
            }
            if(pad.getButtonsDown().contains(GamePadButton.PAD_UP)) {
                this.getLocation().setY(this.getLocation().getY() + 1);
            }
            if(pad.getButtonsDown().contains(GamePadButton.PAD_LEFT)) {
                this.getLocation().setX(this.getLocation().getX() - 1);
            }
            if(pad.getButtonsDown().contains(GamePadButton.PAD_RIGHT)) {
                this.getLocation().setX(this.getLocation().getX() + 1);
            }
        }

    }
}
