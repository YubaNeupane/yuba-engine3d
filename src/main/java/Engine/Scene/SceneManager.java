package Engine.Scene;

import Engine.Core.Utils.Logger;
import Engine.Core.Window;

import java.util.HashMap;

public class SceneManager {

    private static SceneManager instance;

    private final HashMap<String, Scene> sceneHashMap;
    private Scene currentScene = null;

    public SceneManager(){
        this.sceneHashMap = new HashMap<>();
    }

    public static SceneManager get(){
        if (SceneManager.instance == null){
            instance = new SceneManager();
        }
        return instance;
    }

    public void addScene(String name, Scene scene){
        sceneHashMap.put(name, scene);
        if(currentScene == null){
            currentScene = scene;
        }
    }

    public Scene LoadScene(String sceneName){
        if(!sceneHashMap.containsKey(sceneName)){
            Logger.error("SceneManager - " +sceneName + " not in scene manager to be loaded");
        }
        currentScene = get().sceneHashMap.get(sceneName);
        return currentScene;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void UpdateScene(float dt){
        if(currentScene != null){
            currentScene.update(dt);
        }
    }

}
