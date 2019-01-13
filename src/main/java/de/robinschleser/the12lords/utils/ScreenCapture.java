package de.robinschleser.the12lords.utils;

import de.robinschleser.the12lords.Starter;
import org.lwjgl.glfw.GLFWVidMode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;

import static org.lwjgl.glfw.GLFW.*;

public class ScreenCapture {

    public static void saveScreenshot(long window) throws Exception {
        System.out.println("Saving screenshot!");
        Rectangle screenRect = new Rectangle(Starter.xcoord, Starter.ycoord, Starter.widthwindow, Starter.heightwindow);
        BufferedImage capture = new Robot().createScreenCapture(screenRect);
        ImageIO.write(capture, "png", new File("screenshot.png"));
    }

}
