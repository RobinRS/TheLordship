package de.robinschleser.the12lords.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GLSLShaderProgram {

    private int programId;


    public GLSLShaderProgram(String fragmentFile) {
        programId = GL20.glCreateProgram();
        attacheShader(GL20.GL_FRAGMENT_SHADER, fragmentFile);

        GL20.glLinkProgram(programId);
        GL20.glValidateProgram(programId);
    }

    public void enableShaderProgram() {
        GL20.glUseProgram(programId);
    }

    public void disableShaderProgram() {
        GL20.glUseProgram(0);
    }

    private void attacheShader(int shaderType, String file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(GLSLShaderProgram.class.getResourceAsStream(file)));
            StringBuilder builder = new StringBuilder();
            while (reader.ready()) {
                builder.append(reader.readLine() + System.lineSeparator());
            }
            reader.close();

            int id = GL20.glCreateShader(shaderType);
            GL20.glShaderSource(id, builder);
            GL20.glCompileShader(id);
            if(GL20.glGetShaderi(id, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
                throw new RuntimeException("Fail to comp Shader: " + System.lineSeparator() + GL20.glGetShaderInfoLog(id));
            }

            GL20.glAttachShader(programId, id);
            GL20.glDeleteShader(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
        disableShaderProgram();
        GL20.glDeleteProgram(programId);
    }
}
