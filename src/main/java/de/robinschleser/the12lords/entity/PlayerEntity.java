package de.robinschleser.the12lords.entity;
/**
 * Created by Robin on 13.01.2019.
 */

import de.robinschleser.the12lords.input.KeyController;
import de.robinschleser.the12lords.input.MouseController;

import java.util.UUID;

public class PlayerEntity extends Entity implements KeyController, MouseController {

    private UUID uuid;
    private String name;
    private boolean canMove = true;
    private int x,y,z;


    public PlayerEntity(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }



    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }


    @Override
    public boolean isMouseEnabled() {
        return canMove;
    }

    @Override
    public void mouseMovment(int x, int y) {

    }

    @Override
    public void mouseScroll(double xoffset, double yoffset) {

    }

    @Override
    public void mouseLeftUp() {

    }

    @Override
    public void mouseLeftDown() {

    }

    @Override
    public void mouseLeftRepeat() {

    }

    @Override
    public void mouseRightUp() {

    }

    @Override
    public void mouseRightDown() {

    }

    @Override
    public void mouseRightRepeat() {

    }

    @Override
    public void mouseMiddleUp() {

    }

    @Override
    public void mouseMiddleDown() {

    }

    @Override
    public void mouseMiddleRepeat() {

    }

    @Override
    public boolean isKeyBoardEnabled() {
        return canMove;
    }

    @Override
    public void keyPress(int key) {

    }

    @Override
    public void keyRelease(int key) {

    }

    @Override
    public void keyRepeat(int key) {

    }
}
