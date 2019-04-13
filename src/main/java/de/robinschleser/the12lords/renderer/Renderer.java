package de.robinschleser.the12lords.renderer;

import de.robinschleser.the12lords.minimal.Mesh;

public class Renderer {

    public static Mesh rectangle;
    private GLSLShaderProgram shaderProgram;
    public Scene currentScreen;

    public void render() {
        shaderProgram.enableShaderProgram();
        rectangle.render();
    }

    public void destroy() {
        rectangle.destroy();
        shaderProgram.destroy();
    }

    public void init() {
        shaderProgram = new GLSLShaderProgram("/basic.fs");
        rectangle = new Mesh(new int[] {
                0, 1, 3,
                3, 1, 2
        }, new float[] {
                -0.5f, 0.5f, 0,
                -0.5f, -0.5f, 0,
                0.5f, -0.5f, 0,
                0.5f, 0.5f, 0
        });

    }


}
