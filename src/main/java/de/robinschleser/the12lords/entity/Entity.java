package de.robinschleser.the12lords.entity;

import de.robinschleser.the12lords.minimal.Mesh;

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

    public Entity(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.canMove = true;
        this.location = new Position3D();
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

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean canMove() {
        return canMove;
    }
}
