package de.robinschleser.the12lords.input.gamepad;

public enum GamePadButton {

    A(0),
    B(1),
    X(2),
    Y(3),
    LEFT_SHOULDER(4),
    RIGHT_SHOULDER(5),
    BACK(6),
    START(7),
    LEFT_STICK(8),
    RIGHT_STICK(9),
    PAD_UP(10),
    PAD_RIGHT(11),
    PAD_DOWN(12),
    PAD_LEFT(13);

    private int buffIndex;

    GamePadButton(Integer buffIndex) {
        this.buffIndex = buffIndex;
    }

    public int getBuffIndex() {
        return buffIndex;
    }
}
