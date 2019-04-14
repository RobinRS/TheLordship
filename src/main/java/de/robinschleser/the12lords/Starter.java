package de.robinschleser.the12lords;

import de.robinschleser.the12lords.entity.PlayerEntity;
import de.robinschleser.the12lords.input.InputManager;
import de.robinschleser.the12lords.networking.NetworkClient;
import de.robinschleser.the12lords.networking.packets.PingPacket;
import de.robinschleser.the12lords.openglinit.GLFWContextCreator;
import de.robinschleser.the12lords.texturing.TextureringManager;
import org.lwjgl.Version;

import java.util.UUID;

/**
 * Created by Robin on 13.01.2019.
 */
public class Starter {

    // The window handle
    public static long window;
    public static long pingSend;
    private static NetworkClient client;
    public static InputManager inputManager;
    private static TextureringManager textureringManager;
    private static GLFWContextCreator glfwContextCreator;
    public static int xcoord, ycoord, widthwindow, heightwindow;

    /**
     * Starts the engine and creates the gl context
     * Initializes the gameloop
     */
    private static void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        inputManager = new InputManager(window);
        textureringManager = new TextureringManager();

        glfwContextCreator = new GLFWContextCreator();
        glfwContextCreator.createWindow();
        GameLoop loop = new GameLoop(window);

        PlayerEntity playerEntity = new PlayerEntity(UUID.randomUUID(), "Robin");
        inputManager.registerKeyboardController(playerEntity);
        inputManager.registerMouseController(playerEntity);


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

    /**
     * Starts the game engine
     *
     * @param args java startup parameters
     *
     * loads all system library's that are needed
     * starts the engine
     */
    public static void main(String[] args) {

        client = new NetworkClient("localhost", 8810);
        client.connect();
        client.sendPacket(new PingPacket("Client"));
        pingSend = System.currentTimeMillis();

        /*SharedLibraryLoader.load();
        run();*/
    }

}
