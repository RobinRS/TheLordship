package de.robinschleser.the12lords;

import de.robinschleser.the12lords.entity.PlayerEntity;
import de.robinschleser.the12lords.input.InputManager;
import de.robinschleser.the12lords.utils.ScreenCapture;
import org.lwjgl.Version;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.UUID;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Created by Robin on 13.01.2019.
 */
public class Starter {

    // The window handle
    private static long window;
    private static GameLoop loop;
    private static InputManager inputManager;
    public static int xcoord, ycoord, widthwindow, heightwindow;

    public static void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        inputManager = new InputManager(window);

        PlayerEntity playerEntity = new PlayerEntity(UUID.randomUUID(), "Robin");
        inputManager.registerKeyboardController(playerEntity);
        inputManager.registerMouseController(playerEntity);

        init();
        loop = new GameLoop(window);
        loop.runGameLoop();
    }

    private static void init() {
        GLFWErrorCallback.createPrint(System.err).set();
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        window = glfwCreateWindow((int)(vidmode.width() / 1.6), (int)(vidmode.height() / 1.6), "The 12 Lordship", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE ) {
                glfwSetWindowShouldClose(window, true);
            }
            if(key == 283 && action == GLFW_RELEASE) {
                try {
                    ScreenCapture.saveScreenshot(window);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            inputManager.handleKeyboardInput(key, action);
            System.out.println(key + " : " + action);
        });

        glfwSetMouseButtonCallback(window, GLFWMouseButtonCallback.create((window, button, action, mods) -> {
                inputManager.handleMouseClick(button, action);
        }));

        glfwSetCursorPosCallback(window, new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                inputManager.handleMouseMove(xpos, ypos);
            }
        }
        );

        glfwSetScrollCallback(window, GLFWScrollCallback.create((window, xoffset, yoffset) -> {
            inputManager.handleMouseScroll(xoffset, yoffset);
        }));

        glfwSetWindowPosCallback(window, new GLFWWindowPosCallback() {
            @Override
            public void invoke(long window, int x, int y) {
                xcoord = x;
                ycoord = y;
            }
        });

        glfwSetWindowSizeCallback(window, new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                widthwindow = width;
                heightwindow = height;
            }
        });

        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(window, pWidth, pHeight);

            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(window);

        //V-Sync
        glfwSwapInterval(1);

        glfwShowWindow(window);
    }


    public static void main(String[] args) {
        SharedLibraryLoader.load();
        run();
    }

}
