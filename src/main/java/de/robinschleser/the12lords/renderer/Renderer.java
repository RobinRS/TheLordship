package de.robinschleser.the12lords.renderer;

import de.robinschleser.the12lords.entity.Entity;

import java.util.LinkedList;
import java.util.List;

public class Renderer {

    private List<Entity> entities;
    public Scene currentScreen;

    public void render() {
        for (Entity entity : entities) {
            if(entity.getShader() != null && entity.getMesh() != null) {
                if(entity.firstRender){
                    entity.initDebugEntity();
                    entity.firstRender = false;
                    entity.getMesh().setShouldBeRendert(true);
                }
                entity.getShader().enableShaderProgram();
                entity.getShader().addUniformVar("offset");
                entity.getShader().shaderVec3("offset", entity.getLocation().getAsVec3());
                entity.getMesh().render();
            }
        }
    }

    public void destroy() {
        for (Entity entity : entities) {
            entity.getMesh().destroy();
            entity.getShader().destroy();
        }
    }

    public void init() {
        entities = new LinkedList<>();
    }

    public void addEntity(Entity entity) {
       entities.add(entity);
    }


}
