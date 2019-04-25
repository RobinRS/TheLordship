package de.robinschleser.the12lords.gui;


public class Position2D {

    private double x, y;

    public Position2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position2D() {}

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }


}
