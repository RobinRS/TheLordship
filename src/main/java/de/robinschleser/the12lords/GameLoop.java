package de.robinschleser.the12lords;

import de.robinschleser.the12lords.renderer.Renderer;
import org.lwjgl.opengl.GL;

import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Robin on 13.01.2019.
 *
 * Handles rendering of all scenes and there entity's
 *
 */
class GameLoop {

    private Renderer renderer;
    private long window;

    /**
     * Initializes Gameloop
     * @param window where it should render every thing
     */

    GameLoop(long window) {
        this.renderer = new Renderer();
        renderer.init();
        this.window = window;
    }


    /**
     * renders all entity's and clears the gl buffers
     */
    void runGameLoop() {
        GL.createCapabilities();
        glClearColor(0.066f, 0.206f, 0.244f, 0.0f);
        while ( !glfwWindowShouldClose(window) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            renderer.render();

            glfwSwapBuffers(window);
            glfwPollEvents();
            Starter.getFunInit().getDiscord().callCallbacks();
        }

        renderer.destroy();

        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }

    public Renderer getRenderer() {
        return renderer;
    }
}
