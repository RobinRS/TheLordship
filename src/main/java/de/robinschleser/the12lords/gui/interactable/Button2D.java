package de.robinschleser.the12lords.gui.interactable;

import de.robinschleser.the12lords.gui.Object2D;
import de.robinschleser.the12lords.gui.Position2D;
import de.robinschleser.the12lords.input.MouseController;

import java.util.function.Consumer;

public class Button2D extends Object2D implements MouseController {

    int mouseX, mouseY;
    Consumer<Position2D> onClick;
    String text;


    public Button2D(int x, int y, int width, int height, String text , Consumer<Position2D> click) {
        super(new Position2D(x, y), width, height);
        this.onClick = click;
        this.text = text;
    }


    @Override
    public boolean isMouseEnabled() {
        return true;
    }

    @Override
    public void mouseMovment(int x, int y) {
        this.mouseX = x;
        this.mouseY = y;
    }

    @Override
    public void mouseScroll(double xoffset, double yoffset) {

    }

    @Override
    public void mouseLeftUp() {
        onClick.accept(new Position2D(mouseX, mouseY));
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
