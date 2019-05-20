package de.robinschleser.the12lords;

import de.robinschleser.the12lords.entity.PlayerEntity;
import de.robinschleser.the12lords.funnystuff.FunInitializer;
import de.robinschleser.the12lords.input.InputManager;
import de.robinschleser.the12lords.networking.NetworkClient;
import de.robinschleser.the12lords.openglinit.GLFWContextCreator;
import de.robinschleser.the12lords.openglinit.SharedLibraryLoader;
import de.robinschleser.the12lords.texturing.TextureringManager;

import java.util.UUID;

import static de.robinschleser.the12lords.utils.IOUtil.setIcon;

/**
 * Created by Robin on 13.01.2019.
 */
public class Starter {

    // The window handle
    public static long window;
    private static NetworkClient client;
    public static InputManager inputManager;
    private static TextureringManager textureringManager;
    private static GLFWContextCreator glfwContextCreator;
    public static int xcoord, ycoord, widthwindow, heightwindow;
    private static FunInitializer funInit;

    /**
     * Starts the engine and creates the gl context
     * Initializes the gameloop
     */
    private static void run() {
        inputManager = new InputManager(window);
        textureringManager = new TextureringManager();

        funInit = new FunInitializer();

        glfwContextCreator = new GLFWContextCreator();
        glfwContextCreator.createWindow();
        GameLoop loop = new GameLoop(window);
        try {
            setIcon(System.getenv("APPDATA") + "//The12Lordships//icon.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        PlayerEntity playerEntity = new PlayerEntity(UUID.randomUUID(), "Robin");
        inputManager.registerController(playerEntity);


        loop.runGameLoop();
    }

    public static GLFWContextCreator getGlfwContextCreator() {
        return glfwContextCreator;
    }

    public static InputManager getInputManager() {
        return inputManager;
    }

    public static TextureringManager getTextureringManager() {
        return textureringManager;
    }

    public static NetworkClient getClient() {
        return client;
    }

    public static FunInitializer getFunInit() {
        return funInit;
    }

    /**
     * Starts the game engine
     *
     * @param args java startup parameters
     *
     * loads all system library's that are needed
     * starts the engine
     */
    public static void main(String[] args) {
        SharedLibraryLoader.load();
        run();
    }

}
