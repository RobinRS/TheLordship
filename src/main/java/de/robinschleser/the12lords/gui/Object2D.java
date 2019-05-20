package de.robinschleser.the12lords.gui;

import de.robinschleser.the12lords.minimal.Mesh;
import de.robinschleser.the12lords.texturing.Texture;

public class Object2D {

    private Mesh mesh;
    private Texture texture;
    private int width, height;
    private Position2D pos2d;

    public Object2D(Position2D pos2d, int width, int height) {
        this.width = width;
        this.height = height;
        this.pos2d = pos2d;
    }

    public Position2D getPos2d() {
        return pos2d;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Mesh getMesh() {
        return mesh;
    }

}
