package de.robinschleser.the12lords.gui;


public class Position2D {

    private int x, y;

    public Position2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position2D() {}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


}
