package Engine.Scene;

import Engine.Component.MeshRenderComponent;
import Engine.Core.Render.Mesh;
import Engine.Core.Render.SceneRender;
import Engine.Core.Render.Texture.TextureCache;
import Engine.Core.Window;
import Engine.GameObject.GameObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Scene {

    protected String name;
    private final Map<String, GameObject> gameObjects = new HashMap<>();
    private boolean isRunning = false;
    private final Projection projection;
    private final TextureCache textureCache;
    private final Camera camera;


    public Scene(){
        projection = new Projection(Window.getWidth(), Window.getHeight());
        textureCache = new TextureCache();
        camera = new Camera();
    }

    public abstract void init();


    public void start(){
        for(GameObject gameObject: gameObjects.values()){
            gameObject.start();
        }
    }

    public Camera getCamera(){
        return camera;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects.values().stream().toList();
    }

    public GameObject getGameObjectByName(String name){
        return gameObjects.getOrDefault(name,null);
    }

    public void cleanUp(){

    }

    public Projection getProjection() {
        return projection;
    }

    public void resize(int width, int height) {
        projection.updateProjMatrix(width, height);
    }

    public TextureCache getTextureCache() {
        return textureCache;
    }


    public void addGameObjectToScene(GameObject gameObject){
        if(!isRunning){
           gameObjects.put(gameObject.getName(),gameObject);
        }else{
            gameObjects.put(gameObject.getName(),gameObject);
            gameObject.start();
        }
    }


    public abstract void update(float dt);
}