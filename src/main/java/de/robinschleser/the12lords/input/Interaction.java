package de.robinschleser.the12lords.input;

import java.util.ArrayList;

import static de.robinschleser.the12lords.input.Interaction.InteractionType.NONE;
import static de.robinschleser.the12lords.input.Interaction.KeyActionEnum.KEY_NONE;
import static de.robinschleser.the12lords.input.Interaction.MouseActionEnum.MOUSE_NONE;

public class Interaction {

    private static final int EMTY_VALUE = 88888888;

    public enum MouseActionEnum {
        MOVE, RIGHT_DOWN, RIGHT_UP, LEFT_DOWN, LEFT_UP, MIDDLE_DOWN, MIDDLE_UP, SCROLL, MOUSE_NONE
    }
    public enum KeyActionEnum {DOWN, UP, REPEATE, KEY_NONE}
    public enum InteractionType {MouseInteraction, KeyboardInteraction, ControllerInteraction, NONE}

    private double mouseX, mouseY;
    private double offsetX, offsetY;
    private MouseActionEnum mouseAction = MOUSE_NONE;

    private ArrayList<Integer> keysDown;
    private int key;
    private KeyActionEnum keyAction = KEY_NONE;

    private InteractionType interactionType = NONE;

    public Interaction() {
        this.mouseX = 0;
        this.mouseY = 0;
        this.offsetX = 0;
        this.offsetY = 0;
        this.key = EMTY_VALUE;
        this.keysDown = new ArrayList<>(); //Key Amount according to https://de.wikipedia.org/wiki/Tastatur
    }

    protected void setMouseAction(MouseActionEnum action) {
        this.mouseAction = action;
    }

    public MouseActionEnum getMouseAction() {
        return mouseAction;
    }

    protected void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    public double getMouseX() {
        return mouseX;
    }

    protected void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }

    public double getMouseY() {
        return mouseY;
    }

    protected void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }

    public double getOffsetX() {
        return offsetX;
    }

    protected void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }

    public double getOffsetY() {
        return offsetY;
    }

    protected void setKeyPessed(int keyCode) {
        if(!keysDown.contains(keyCode))
            keysDown.add(keyCode);
    }

    protected void setKeyRelease(int keyCode) {
        if(keysDown.contains(keyCode))
            keysDown.remove(Integer.getInteger("" + keyCode));
    }

    protected void setKey(int key) {
        this.key = key;
    }

    protected void setKeyAction(KeyActionEnum keyAction) {
        this.keyAction = keyAction;
    }

    public ArrayList<Integer> getKeysDown() {
        return keysDown;
    }

    public int getKey() {
        return key;
    }

    public KeyActionEnum getKeyAction() {
        return keyAction;
    }

    public InteractionType getInteractionType() {
        return interactionType;
    }

    protected void setInteractionType(InteractionType interactionType) {
        this.interactionType = interactionType;
    }
}
