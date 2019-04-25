package de.robinschleser.the12lords.entity;

import de.robinschleser.the12lords.input.Interaction;
import de.robinschleser.the12lords.renderer.Renderer;

import java.util.UUID;

import static de.robinschleser.the12lords.input.Interaction.InteractionType.KeyboardInteraction;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_0;

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
                    Renderer.rectangle.setShouldBeRendert(true);
                }else if(action.getKeyAction() == Interaction.KeyActionEnum.UP) {
                    Renderer.rectangle.setShouldBeRendert(false);
                }
            }
        }
    }
}
