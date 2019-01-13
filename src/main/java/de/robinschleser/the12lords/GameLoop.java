package de.robinschleser.the12lords;

import de.robinschleser.the12lords.input.InputManager;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Robin on 13.01.2019.
 */
public class GameLoop {

    private InputManager manager;
    private long window;

    public GameLoop(long window) {
        this.manager = new InputManager();
        this.window = window;
    }


    public void runGameLoop() {
        GL.createCapabilities();
        glClearColor(0.066f, 0.206f, 0.244f, 0.0f);

        while ( !glfwWindowShouldClose(window) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glfwSwapBuffers(window);
            glfwPollEvents();
        }

    }


}
