package Game.Scene;

import Engine.Core.Utils.Logger;
import Engine.GameObject.GameObject;
import Engine.GameObject.Primitives.Cube;
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
        cube.setPosition(0,0,-2);

        addGameObjectToScene(cube);
    }



    @Override
    public void update(float dt) {
        rotation += 0.5;
        if (rotation > 360) {
            rotation = 0;
        }

        getGameObjectByName("Cube").setRotation(1, 1, 1, (float) Math.toRadians(rotation));

//        getGameObjects().getFirst().getComponent(TransformComponent.class).updateModelMatrix();
    }
}
