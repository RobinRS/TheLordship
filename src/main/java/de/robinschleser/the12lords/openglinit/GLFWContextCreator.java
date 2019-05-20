package de.robinschleser.the12lords.openglinit;

import de.robinschleser.the12lords.Starter;
import de.robinschleser.the12lords.utils.ScreenCapture;
import org.lwjgl.glfw.*;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GLFWContextCreator {

    private boolean ready = true;

    public void createWindow() {
        GLFWErrorCallback.createPrint(System.err).set();
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        Starter.window = glfwCreateWindow((int)(vidmode.width() / 1.6), (int)(vidmode.height() / 1.6), "The 12 Lordship", NULL, NULL);
        if ( Starter.window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(Starter.window, pWidth, pHeight);

            glfwSetWindowPos(
                    Starter.window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(Starter.window);

        //V-Sync
        glfwSwapInterval(1);
        glfwShowWindow(Starter.window);
        createCallbacks();
    }

    private void createCallbacks() {
        glfwSetKeyCallback(Starter.window, (window, key, scancode, action, mods) -> {
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
            Starter.inputManager.handleKeyboardInput(key, action);
        });

        glfwSetMouseButtonCallback(Starter.window, GLFWMouseButtonCallback.create((window, button, action, mods) -> {
            Starter.inputManager.handleMouseClick(button, action);
        }));

        glfwSetCursorPosCallback(Starter.window, new GLFWCursorPosCallback() {
                    @Override
                    public void invoke(long window, double xpos, double ypos) {
                        Starter.inputManager.handleMouseMove(xpos, ypos);
                    }
                }
        );

        glfwSetScrollCallback(Starter.window, GLFWScrollCallback.create((window, xoffset, yoffset) -> {
            Starter.inputManager.handleMouseScroll(xoffset, yoffset);
        }));

        glfwSetWindowPosCallback(Starter.window, new GLFWWindowPosCallback() {
            @Override
            public void invoke(long window, int x, int y) {
                Starter.xcoord = x;
                Starter.ycoord = y;
            }
        });

        glfwSetWindowSizeCallback(Starter.window, new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                Starter.widthwindow = width;
                Starter.heightwindow = height;

            }
        });
    }

}
