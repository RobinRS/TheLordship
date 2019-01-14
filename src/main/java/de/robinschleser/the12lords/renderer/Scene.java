package de.robinschleser.the12lords.renderer;

import de.robinschleser.the12lords.entity.Entity;
import de.robinschleser.the12lords.gui.Object2D;
import de.robinschleser.the12lords.minimal.Mesh;

import java.util.ArrayList;

public class Scene {

    public GLSLShaderProgram currentShaderProgram;
    public ArrayList<Mesh> toRender;
    public double screenType;

    public Scene(double screenType) {
        this.screenType = screenType;
        this.currentShaderProgram = null;
        this.toRender = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        toRender.add(entity.getMesh());
    }

    public void add2DObject(Object2D object) {
        toRender.add(object.getMesh());
    }

    public void renderScene() {
        for (Mesh mesh : toRender) {
            mesh.render();
        }
    }





}
