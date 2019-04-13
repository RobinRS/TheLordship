package de.robinschleser.the12lords.entity;

import de.robinschleser.the12lords.input.KeyController;
import de.robinschleser.the12lords.input.MouseController;

import java.util.UUID;

import static org.lwjgl.glfw.GLFW.*;

public class MovingEntity extends LivingEntity implements MouseController, KeyController {

    MovingEntity(UUID uuid, String name) {
        super(uuid, name);
    }

    @Override
    public boolean isKeyBoardEnabled() {
        return canMove();
    }

    @Override
    public void keyPress(int key) {
        if(key == GLFW_KEY_W) {
            getLocation().setX(getLocation().getX() + 2);
            System.out.println("W press");
        }
        if(key == GLFW_KEY_S) {
            getLocation().setX(getLocation().getX() - 2);
        }

        if(key == GLFW_KEY_SPACE) {
            getLocation().setY(getLocation().getY() + 2);
        }

        if(key == GLFW_KEY_A) {
            getLocation().setZ(getLocation().getZ() - 2);
        }
        if(key == GLFW_KEY_D) {
            getLocation().setZ(getLocation().getZ() + 2);
        }
    }

    @Override
    public void keyRelease(int key) {

    }

    @Override
    public void keyRepeat(int key) {

        if(key == GLFW_KEY_W) {
            getLocation().setX(getLocation().getX() + 1);
        }
        if(key == GLFW_KEY_S) {
            getLocation().setX(getLocation().getX() - 1);
        }

        if(key == GLFW_KEY_SPACE) {
            getLocation().setY(getLocation().getY() + 1);
        }

        if(key == GLFW_KEY_A) {
            getLocation().setZ(getLocation().getZ() - 1);
        }
        if(key == GLFW_KEY_D) {
            getLocation().setZ(getLocation().getZ() + 1);
        }
    }

    @Override
    public boolean isMouseEnabled() {
        return canMove();
    }

    private int lastX;
    private int lastY;

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
    public void mouseRightUp() {

    }

    @Override
    public void mouseRightDown() {

    }

    @Override
    public void mouseMiddleUp() {

    }

    @Override
    public void mouseMiddleDown() {

    }

}
