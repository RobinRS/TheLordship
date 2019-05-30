package de.robinschleser.the12lords.entity;

import de.robinschleser.the12lords.input.Interaction;

import java.util.UUID;

import static de.robinschleser.the12lords.input.Interaction.InteractionType.KeyboardInteraction;
import static org.lwjgl.glfw.GLFW.*;

public class PlayerEntity extends MovingEntity {

    public PlayerEntity(UUID uuid, String name) {
        super(uuid, name);
        setCanMove(true);
    }

    @Override
    public void interaction(Interaction action) {
        super.interaction(action);

        if(KeyboardInteraction == action.getInteractionType()) {
            if(action.getKey() == GLFW_KEY_0) {
                if(action.getKeyAction() == Interaction.KeyActionEnum.DOWN) {
                    this.initDebugEntity();
                    this.getMesh().setShouldBeRendert(true);
                }
            }
            if(action.getKey() == GLFW_KEY_UP) {
                this.getLocation().setY(this.getLocation().getY() + 10);
                System.out.println("Up");
            }

            if(action.getKey() == GLFW_KEY_DOWN) {
                this.getLocation().setY(this.getLocation().getY() - 10);
                System.out.println("Down");
            }

            if(action.getKey() == GLFW_KEY_LEFT) {
                this.getLocation().setX(this.getLocation().getX() + 10);
                System.out.println("Left");
            }

            if(action.getKey() == GLFW_KEY_RIGHT) {
                this.getLocation().setX(this.getLocation().getX() - 10);
                System.out.println("Right");
            }
        }
    }
}
