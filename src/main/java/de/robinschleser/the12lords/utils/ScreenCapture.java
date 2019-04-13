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
        Rectangle screenRect = new Rectangle(Starter.xcoord, Starter.ycoord, Starter.widthwindow, Starter.heightwindow);
        BufferedImage capture = new Robot().createScreenCapture(screenRect);
        File gameFolder = new File(System.getenv("APPDATA") + "//The12Lordships//");
        if(!gameFolder.isDirectory())
            gameFolder.mkdirs();

        File screenShotFolder = new File(System.getenv("APPDATA") + "//The12Lordships//screenshots//");
        if(!screenShotFolder.isDirectory())
            screenShotFolder.mkdirs();

        ImageIO.write(capture, "png", new File(gameFolder.getAbsolutePath() + "//screenshots//screenshot"+ System.currentTimeMillis()+".png"));
    }

}
