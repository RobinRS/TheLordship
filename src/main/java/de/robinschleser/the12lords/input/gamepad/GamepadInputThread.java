package de.robinschleser.the12lords.input.gamepad;

import de.robinschleser.the12lords.Starter;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class GamepadInputThread {

    public void checkForNewGamepad() {
        int active = 0;
        for (int i = 0; i < 16; i++) {
            String name = glfwGetJoystickName(i);
            if (name != null) {
                active++;
                boolean found = false;
                for (Gamepad gp : Starter.getInputManager().getPads()) {
                    if (gp.getGamepadId() == i) {
                        found = true;
                    }
                }
                if (!found) {
                    Gamepad pad = new Gamepad(i, name);
                    Starter.getInputManager().handleNewGamepad(pad);
                    System.out.println("Created new Gamepad");
                }
            }
        }
        Starter.getInputManager().setActivePads(active);
    }
    public void checkForInput() {
        for (Gamepad pad : Starter.getInputManager().getPads()) {
            ByteBuffer byteBuf = glfwGetJoystickButtons(pad.getGamepadId());
            if (byteBuf != null) {
                for (int i = 0; i < byteBuf.capacity(); i++) {
                    pad.handleGamepadButtons(GamePadButton.values()[i], byteBuf.get(i));
                    Starter.getInputManager().handelPadInput(pad);
                }
            }

            FloatBuffer floatBuf = glfwGetJoystickAxes(pad.getGamepadId());
            if (floatBuf != null) {
                for (int i = 0; i < floatBuf.capacity(); i++) {
                    System.out.print(floatBuf.get(i) + " ");

                    //pad.handleGamepadAxis(i , floatBuf.get(i));
                    //Starter.getInputManager().handelPadInput(pad);

                }
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}