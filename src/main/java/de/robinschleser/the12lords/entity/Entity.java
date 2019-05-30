package de.robinschleser.the12lords.entity;

import de.robinschleser.the12lords.minimal.Mesh;
import de.robinschleser.the12lords.renderer.GLSLShaderProgram;
import org.joml.Vector3f;

import java.util.UUID;

/**
 * Created by Robin on 13.01.2019.
 */
public class Entity {

    private UUID uuid;
    private String name;
    private Mesh mesh;
    private Position3D location;
    private boolean canMove;

    private GLSLShaderProgram shader;

    Entity(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.canMove = true;
        this.location = new Position3D();

    }

    public void initDebugEntity() {
        this.shader = new GLSLShaderProgram("/entity.vs", "/entity.fs", "positionsIn");
        this.shader.shaderVec3("offset", new Vector3f(0.5f, 0.5f, 0.0f));
        this.mesh = new Mesh(new int[] {
                0, 1, 3,
                3, 1, 2
        }, new float[] {
                -0.5f, 0.5f, 0,
                -0.5f, -0.5f, 0,
                0.5f, -0.5f, 0,
                0.5f, 0.5f, 0.0f
        });
    }

    public Position3D getLocation() {
        return location;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public String getName() {
        return name;
    }

    public UUID getUUID() {
        return uuid;
    }

    void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    boolean canMove() {
        return canMove;
    }

    public GLSLShaderProgram getShader() {
        return shader;
    }

}
