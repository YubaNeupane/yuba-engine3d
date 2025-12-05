package Game.Scene;

import Engine.Component.Component;
import Engine.Component.MeshRenderComponent;
import Engine.Component.TransformComponent;
import Engine.Core.Render.Mesh;
import Engine.GameObject.GameObject;
import Engine.GameObject.Primitives.Cube;
import Engine.Scene.Scene;
import Engine.Utils.Debug;

public class TestScene extends Scene {

    public TestScene(){
        Debug.info("Inside Test Scene");
    }

    double rotation = 0.0;

    @Override
    public void init() {
//        float[] positions = new float[]{
//                -0.5f, 0.5f, -1.0f,
//                -0.5f, -0.5f, -1.0f,
//                0.5f, -0.5f, -1.0f,
//                0.5f, 0.5f, -1.0f,
//        };
//        float[] colors = new float[]{
//                0.5f, 0.0f, 0.0f,
//                0.0f, 0.5f, 0.0f,
//                0.0f, 0.0f, 0.5f,
//                0.0f, 0.5f, 0.5f,
//        };
//        int[] indices = new int[]{
//                0, 1, 3, 3, 1, 2,
//        };
//        Mesh mesh = new Mesh(positions, colors, indices);
//
//        GameObject gameObject = new GameObject("Trangle");
//        gameObject.addComponent(new MeshRenderComponent(mesh));

//        addGameObjectToScene(gameObject);

        GameObject cube = new Cube();
        TransformComponent transformComponent = cube.getComponent(TransformComponent.class);
        transformComponent.setPosition(0,0,-2);

        addGameObjectToScene(cube);
    }



    @Override
    public void update(float dt) {
        rotation += 1.5;
        if (rotation > 360) {
            rotation = 0;
        }
        getGameObjects().getFirst().getComponent(TransformComponent.class).setRotation((float) Math.toRadians(rotation), 1, 1, (float) Math.toRadians(rotation));

//        getGameObjects().getFirst().getComponent(TransformComponent.class).updateModelMatrix();
    }
}
