package de.robinschleser.the12lords.renderer;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class GLSLShaderProgram {

    private int programId;
    private HashMap<String, Integer> shaderVariables;

    public GLSLShaderProgram(String fragmentFile) {
        shaderVariables = new HashMap<>();
        programId = GL20.glCreateProgram();
        attacheShader(GL20.GL_FRAGMENT_SHADER, fragmentFile);

        GL20.glLinkProgram(programId);
        GL20.glValidateProgram(programId);
    }

    public GLSLShaderProgram(String vertexShader, String fragmentFile, String... vertexAttribute) {
        shaderVariables = new HashMap<>();
        programId = GL20.glCreateProgram();
        attacheShader(GL20.GL_VERTEX_SHADER, vertexShader);
        attacheShader(GL20.GL_FRAGMENT_SHADER, fragmentFile);

        for (int i = 0; i < vertexAttribute.length; i++) {
            GL20.glBindAttribLocation(programId, i, vertexAttribute[i]);
        }

        GL20.glLinkProgram(programId);
        GL20.glValidateProgram(programId);
    }

    public void addUniformVar(String var) {
        int local = GL20.glGetUniformLocation(programId, var);
        shaderVariables.put(var, local);
    }

    public void shaderVec3(String var, Vector3f vector3f) {
        if(!shaderVariables.containsKey(var))
            return;
        int local = shaderVariables.get(var);
        GL20.glUniform3f(local, vector3f.x, vector3f.y, vector3f.z);
    }

    void enableShaderProgram() {
        GL20.glUseProgram(programId);
    }

    private void disableShaderProgram() {
        GL20.glUseProgram(0);
    }

    private void attacheShader(int shaderType, String file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(GLSLShaderProgram.class.getResourceAsStream(file)));
            StringBuilder builder = new StringBuilder();
            while (reader.ready()) {
                builder.append(reader.readLine()).append(System.lineSeparator());
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

    void destroy() {
        disableShaderProgram();
        GL20.glDeleteProgram(programId);
    }
}
