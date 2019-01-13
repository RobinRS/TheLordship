package de.robinschleser.the12lords.minimal;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Mesh {

    private int vao;
    private int[] vbos;
    private int vertexCount;
    private boolean indexBuffer;
    private int indexBufferVBO;
    private boolean shouldBeRendert = true;
    private boolean isRendered = true;
    private int[] indices;
    private float[] position;

    public Mesh(int[] indices, float[] position) {
        this.indices = indices;
        this.position = position;
        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        indexBufferVBO = attacheIndexBuffer(indices);
        int positionVBO = addStaticAttribute(0, position, 3);

        vbos = new int[] {positionVBO};
        vertexCount = indices.length;
        indexBuffer = true;
    }

    public Mesh(float[] position) {
        this.position = position;
        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);
        int positionVBO = addStaticAttribute(0, position, 3);
        vbos = new int[] {positionVBO};
        vertexCount = position.length / 3;
    }

    public void initAgain() {
        if(indexBuffer) {
            vao = GL30.glGenVertexArrays();
            GL30.glBindVertexArray(vao);

            indexBufferVBO = attacheIndexBuffer(indices);
            int positionVBO = addStaticAttribute(0, position, 3);

            vbos = new int[] {positionVBO};
            vertexCount = indices.length;
            indexBuffer = true;
        }else{
            vao = GL30.glGenVertexArrays();
            GL30.glBindVertexArray(vao);
            int positionVBO = addStaticAttribute(0, position, 3);
            vbos = new int[] {positionVBO};
            vertexCount = position.length / 3;
        }
    }

    private void OpenGLRender() {
        GL30.glBindVertexArray(vao);
        for (int i = 0; i < vbos.length; i++) {
            GL20.glEnableVertexAttribArray(i);
        }
        if(indexBuffer) {
            GL11.glDrawElements(GL11.GL_TRIANGLES, vertexCount, GL11.GL_UNSIGNED_INT, 0);
        }else {
            GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vertexCount);
        }
        for (int i = 0; i < vbos.length; i++) {
            GL20.glDisableVertexAttribArray(i);
        }
    }

    public void render() {
        if(shouldBeRendert()) {
            if(!isRendered()) {
                initAgain();
            }

            OpenGLRender();
        }else{
            destroy();
        }
    }

    private int addStaticAttribute(int index, float[] data, int dataSize) {
        int vbo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, data, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(index, dataSize, GL11.GL_FLOAT, false, 0, 0);
        return vbo;
    }

    private int attacheIndexBuffer(int[] indices) {
        int vbo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vbo);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);
        return vbo;
    }

    public void destroy() {
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glDeleteVertexArrays(vao);
        for (int id : vbos) {
            GL15.glDeleteBuffers(id);
        }
        if(indexBuffer) {
            GL15.glDeleteBuffers(indexBufferVBO);
        }
        isRendered = false;
    }

    public boolean isRendered() {
        return isRendered;
    }

    public void setShouldBeRendert(boolean shouldBeRendert) {
        this.shouldBeRendert = shouldBeRendert;
    }

    public boolean shouldBeRendert() {
        return shouldBeRendert;
    }
}
