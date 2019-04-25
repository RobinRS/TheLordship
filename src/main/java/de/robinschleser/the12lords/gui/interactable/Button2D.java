package de.robinschleser.the12lords.gui.interactable;

import de.robinschleser.the12lords.gui.Object2D;
import de.robinschleser.the12lords.gui.Position2D;
import de.robinschleser.the12lords.input.Controller;
import de.robinschleser.the12lords.input.Interaction;

import java.util.function.Consumer;

import static de.robinschleser.the12lords.input.Interaction.InteractionType.MouseInteraction;
import static de.robinschleser.the12lords.input.Interaction.MouseActionEnum.LEFT_DOWN;

public class Button2D extends Object2D implements Controller {

    Consumer<Position2D> onClick;
    String text;


    public Button2D(int x, int y, int width, int height, String text , Consumer<Position2D> click) {
        super(new Position2D(x, y), width, height);
        this.onClick = click;
        this.text = text;
    }


    @Override
    public void interaction(Interaction action) {
        if(action.getInteractionType() == MouseInteraction) {
            if(action.getMouseAction() == LEFT_DOWN) {
                onClick.accept(new Position2D(action.getMouseX(), action.getMouseY()));
            }
        }
    }
}
