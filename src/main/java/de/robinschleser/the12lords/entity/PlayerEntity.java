package de.robinschleser.the12lords.entity;
/**
 * Created by Robin on 13.01.2019.
 */

import de.robinschleser.the12lords.input.KeyController;

import java.util.UUID;

public class PlayerEntity extends Entity implements KeyController {

    private UUID uuid;
    private String name;
    private boolean canMove;


    public PlayerEntity(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }


    @Override
    public boolean isEnabled() {
        return canMove;
    }

    @Override
    public void keyDown() {

    }

    @Override
    public void keyUp() {

    }

    @Override
    public void keyPress() {

    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
