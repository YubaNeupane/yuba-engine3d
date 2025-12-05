package Engine.Scene;

import Engine.Component.MeshRenderComponent;
import Engine.Core.Render.Mesh;
import Engine.Core.Render.SceneRender;
import Engine.GameObject.GameObject;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {

    protected String name;
    private List<GameObject> gameObjects = new ArrayList<>();
    private boolean isRunning = false;
    private SceneRender sceneRender;


    public Scene(){
    }

    public abstract void init();


    public void start(){
        for(GameObject gameObject: gameObjects){
            gameObject.start();
        }
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void cleanUp(){

    }


    public void addGameObjectToScene(GameObject gameObject){
        if(!isRunning){
           gameObjects.add(gameObject);
        }else{
            gameObjects.add(gameObject);
            gameObject.start();
        }
    }


    public abstract void update(float dt);
}