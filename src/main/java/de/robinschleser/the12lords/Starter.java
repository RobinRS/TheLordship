package de.robinschleser.the12lords;

import de.robinschleser.the12lords.entity.PlayerEntity;
import de.robinschleser.the12lords.input.InputManager;
import de.robinschleser.the12lords.openglinit.GLFWContextCreator;
import de.robinschleser.the12lords.openglinit.SharedLibraryLoader;
import de.robinschleser.the12lords.texturing.TextureringManager;
import de.robinschleser.the12lords.utils.ScreenCapture;
import org.lwjgl.Version;
import org.lwjgl.glfw.*;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.UUID;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Created by Robin on 13.01.2019.
 */
public class Starter {

    // The window handle
    public static long window;
    public static GameLoop loop;
    public static InputManager inputManager;
    public static TextureringManager textureringManager;
    public static GLFWContextCreator glfwContextCreator;
    public static int xcoord, ycoord, widthwindow, heightwindow;

    public static void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        inputManager = new InputManager(window);
        textureringManager = new TextureringManager();

        glfwContextCreator = new GLFWContextCreator();
        glfwContextCreator.createWindow();
        loop = new GameLoop(window);

        PlayerEntity playerEntity = new PlayerEntity(UUID.randomUUID(), "Robin");
        inputManager.registerKeyboardController(playerEntity);
        inputManager.registerMouseController(playerEntity);


        loop.runGameLoop();
    }


    public static void main(String[] args) {
        SharedLibraryLoader.load();
        run();
    }

}
