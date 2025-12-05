package Engine.Scene;

import Engine.Component.MeshRenderComponent;
import Engine.Core.Render.Mesh;
import Engine.Core.Render.SceneRender;
import Engine.Core.Window;
import Engine.GameObject.GameObject;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {

    protected String name;
    private List<GameObject> gameObjects = new ArrayList<>();
    private boolean isRunning = false;
    private SceneRender sceneRender;
    private Projection projection;


    public Scene(){
        projection = new Projection(Window.getWidth(), Window.getHeight());
        init();
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

    public Projection getProjection() {
        return projection;
    }

    public void resize(int width, int height) {
        projection.updateProjMatrix(width, height);
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