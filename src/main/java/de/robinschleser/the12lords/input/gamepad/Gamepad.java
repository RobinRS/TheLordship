package de.robinschleser.the12lords.input.gamepad;

import java.util.LinkedList;
import java.util.List;


public class Gamepad {

    private boolean active;
    private int gamepadId;
    private String gamepadName;
    private float axisLeft, axisRight;
    private List<GamePadButton> buttonsDown;

    private GamePadAxis leftHorizontalState;
    private GamePadAxis leftVerticalState;

    private GamePadAxis rightHorizontalState;
    private GamePadAxis rightVerticalState;

    Gamepad(int gamepadId, String gamepadName) {
        this.gamepadId = gamepadId;
        this.gamepadName = gamepadName;
        this.buttonsDown = new LinkedList<>();
    }

    public boolean isActive() {
        return active;
    }

    protected void setActive(boolean active) {
        this.active = active;
    }

    int getGamepadId() {
        return gamepadId;
    }

    public String getGamepadName() {
        return gamepadName;
    }

    public float getAxisLeft() {
        return axisLeft;
    }

    protected void setAxisLeft(long axisLeft) {
        this.axisLeft = axisLeft;
    }

    public float getAxisRight() {
        return axisRight;
    }

    protected void setAxisRight(long axisRight) {
        this.axisRight = axisRight;
    }

    public List<GamePadButton> getButtonsDown() {
        return buttonsDown;
    }


    void handleGamepadButtons(GamePadButton button, byte b) {
        if(b == (byte)0) {
            buttonsDown.remove(button);
        }else{
            buttonsDown.add(button);
        }
    }

    public void handleGamepadAxis(int i, float v) {
        if(i == 0) {
            this.axisLeft = v;
        }else{
            this.axisRight = v;
        }
    }
}
