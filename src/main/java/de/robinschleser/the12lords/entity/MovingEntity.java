package de.robinschleser.the12lords.entity;

import de.robinschleser.the12lords.input.Controller;
import de.robinschleser.the12lords.input.Interaction;

import java.util.UUID;

import static de.robinschleser.the12lords.input.Interaction.InteractionType.*;

public class MovingEntity extends LivingEntity implements Controller {

    MovingEntity(UUID uuid, String name) {
        super(uuid, name);
    }

    @Override
    public void interaction(Interaction action) {
        if(action.getInteractionType() == MouseInteraction) {
            System.out.println(action.getMouseX() + " : " + action.getMouseY()+ " ("+action.getMouseAction()+")");
        }
        if(action.getInteractionType() == KeyboardInteraction) {
            System.out.println(action.getKey() + "("+action.getKeyAction()+") " + action.getKeysDown().toArray(new Integer[50]).toString());
        }
        if(action.getInteractionType() == ControllerInteraction) {

        }
    }
}
