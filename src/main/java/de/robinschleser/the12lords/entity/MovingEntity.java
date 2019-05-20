package de.robinschleser.the12lords.entity;

import de.robinschleser.the12lords.input.Controller;
import de.robinschleser.the12lords.input.Interaction;

import java.util.UUID;

public class MovingEntity extends LivingEntity implements Controller {

    MovingEntity(UUID uuid, String name) {
        super(uuid, name);
    }

    @Override
    public void interaction(Interaction action) {
    }
}
