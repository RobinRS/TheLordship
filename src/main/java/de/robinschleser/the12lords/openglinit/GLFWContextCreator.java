package de.robinschleser.the12lords.openglinit;

import de.robinschleser.the12lords.utils.ScreenCapture;
import org.lwjgl.glfw.*;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static de.robinschleser.the12lords.Starter.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GLFWContextCreator {

    public void createWindow() {
        GLFWErrorCallback.createPrint(System.err).set();
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        assert vidmode != null;
        window = glfwCreateWindow((int)(vidmode.width() / 1.6), (int)(vidmode.height() / 1.6), "The 12 Lordship", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");
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
        createCallbacks();
    }

    private void createCallbacks() {
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
        });

        glfwSetMouseButtonCallback(window, GLFWMouseButtonCallback.create((window, button, action, mods) -> inputManager.handleMouseClick(button, action)));

        glfwSetCursorPosCallback(window, new GLFWCursorPosCallback() {
                    @Override
                    public void invoke(long window, double xpos, double ypos) {
                        inputManager.handleMouseMove(xpos, ypos);
                    }
                }
        );

        glfwSetScrollCallback(window, GLFWScrollCallback.create((window, xoffset, yoffset) -> inputManager.handleMouseScroll(xoffset, yoffset)));

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
    }



}
