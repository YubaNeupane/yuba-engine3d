package Game.Scene;

import Engine.Core.Render.Model;
import Engine.Core.Utils.Logger;
import Engine.GameObject.CustomGameObject;
import Engine.GameObject.GameObject;
import Engine.GameObject.Primitives.Cube;
import Engine.Scene.ModelLoader;
import Engine.Scene.Scene;
import Engine.Utils.Debug;
import Engine.Utils.Time;

public class TestScene extends Scene {

    public TestScene(){
        Debug.info("Inside Test Scene");
    }

    double rotation = 0.0;

    @Override
    public void init() {
        GameObject cube = new Cube();
//        GameObject cube2 = new Cube("Cube 2");
//
//        cube.setPosition(0,0,-2);
//        cube.setPosition(0,0,-1);
//
//        addGameObjectToScene(cube);
//        addGameObjectToScene(cube2);

        int count = 0;
//        Model cubeModel = ModelLoader.loadModel("cube-model","resources/models/cube/bunny.obj",getTextureCache());
//        for (int x = 0; x < 20; x ++){
//            for (int z = 0; z < 20; z++){
//                GameObject gameObject = new CustomGameObject("cude-model"+count,cubeModel);
//                gameObject.setPosition(x, 0, z * -1);
//
//
//                addGameObjectToScene(gameObject);
//                gameObject.setScale(0.5f);
//                count++;
//            }
//        }
        Model cubeModel = ModelLoader.loadModel("cube-model","resources/models/cube/bunny.obj",getTextureCache());
        GameObject gameObject = new CustomGameObject("cude-model",cubeModel);
        gameObject.setPosition(0, 0, -2);
        gameObject.setScale(3);
        addGameObjectToScene(gameObject);
//
//        Model cubeModel2 = ModelLoader.loadModel("cube-model2","resources/models/cube/cube.obj",getTextureCache());
//        GameObject gameObject2 = new CustomGameObject("cude-model2",cubeModel2);
//        gameObject.setPosition(1, 0, -1);
//        addGameObjectToScene(gameObject2);




    }



    @Override
    public void update(float dt) {
//        rotation += 0.5;
//        if (rotation > 360) {
//            rotation = 0;
//        }

//        getGameObjectByName("Cube").setRotation(1, 1, 1, (float) Math.toRadians(rotation));

//        getGameObjects().getFirst().getComponent(TransformComponent.class).updateModelMatrix();
    }
}
