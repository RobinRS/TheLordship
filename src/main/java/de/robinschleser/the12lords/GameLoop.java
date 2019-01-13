package de.robinschleser.the12lords;

import de.robinschleser.the12lords.input.InputManager;
import de.robinschleser.the12lords.renderer.Renderer;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Robin on 13.01.2019.
 */
public class GameLoop {

    private InputManager manager;
    private Renderer renderer;
    private long window;

    public GameLoop(long window) {
        this.manager = new InputManager(window);
        this.renderer = new Renderer();
        this.window = window;
    }


    public void runGameLoop() {
        GL.createCapabilities();
        glClearColor(0.066f, 0.206f, 0.244f, 0.0f);
        renderer.init();
        while ( !glfwWindowShouldClose(window) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            renderer.render();

            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        renderer.destroy();

        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }


}
