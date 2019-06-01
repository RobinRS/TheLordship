package de.robinschleser.the12lords.input;

import de.robinschleser.the12lords.input.gamepad.Gamepad;

import java.util.ArrayList;
import java.util.List;

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

    private int activeGamepads = 0;
    private List<Gamepad> pads;
    private Gamepad lastPad;

    private InteractionType interactionType = NONE;

    Interaction() {
        this.mouseX = 0;
        this.mouseY = 0;
        this.offsetX = 0;
        this.offsetY = 0;
        this.key = EMTY_VALUE;
        this.keysDown = new ArrayList<>();
        this.pads = new ArrayList<>();
    }

    void setMouseAction(MouseActionEnum action) {
        this.mouseAction = action;
    }

    public MouseActionEnum getMouseAction() {
        return mouseAction;
    }

    void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    public double getMouseX() {
        return mouseX;
    }

    void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }

    public double getMouseY() {
        return mouseY;
    }

    void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }

    public double getOffsetX() {
        return offsetX;
    }

    void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }

    public double getOffsetY() {
        return offsetY;
    }

    void setKeyPessed(int keyCode) {
        if(!keysDown.contains(keyCode))
            keysDown.add(keyCode);
    }

    void setKeyRelease(int keyCode) {
        for (int i = 0; i < keysDown.size(); i++) {
            if(keysDown.get(i) == keyCode){
                keysDown.remove(i);
            }
        }
    }

    void setKey(int key) {
        this.key = key;
    }

    void setKeyAction(KeyActionEnum keyAction) {
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

    void setInteractionType(InteractionType interactionType) {
        this.interactionType = interactionType;
    }

    void addPad(Gamepad pad) {
        pads.add(pad);
    }

    List<Gamepad> getPads() {
        return pads;
    }

    void setActiveGamepads(int activeGamepads) {
        this.activeGamepads = activeGamepads;
    }

    public int getActiveGamepads() {
        return activeGamepads;
    }

    void setLastPad(Gamepad lastPad) {
        this.lastPad = lastPad;
    }

    public Gamepad getLastPad() {
        return lastPad;
    }

}
