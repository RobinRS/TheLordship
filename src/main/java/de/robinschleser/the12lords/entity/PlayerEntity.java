package de.robinschleser.the12lords.entity;

import de.robinschleser.the12lords.renderer.Renderer;

import java.util.UUID;

public class PlayerEntity extends MovingEntity {

    public PlayerEntity(UUID uuid, String name) {
        super(uuid, name);
        setCanMove(true);
    }

    @Override
    public void keyPress(int key) {
        super.keyPress(key);
    }

    @Override
    public void mouseLeftUp() {
        super.mouseLeftUp();
        Renderer.rectangle.setShouldBeRendert(false);
    }

    @Override
    public void mouseMiddleUp() {
        super.mouseMiddleUp();
        Renderer.rectangle.setShouldBeRendert(true);
    }

}
