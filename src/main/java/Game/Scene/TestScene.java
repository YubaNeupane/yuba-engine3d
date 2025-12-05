package Game.Scene;

import Engine.Component.MeshRenderComponent;
import Engine.Core.Render.Mesh;
import Engine.GameObject.GameObject;
import Engine.Scene.Scene;
import Engine.Utils.Debug;

public class TestScene extends Scene {

    public TestScene(){
        Debug.info("Inside Test Scene");
    }

    @Override
    public void init() {
        float[] positions = new float[]{
                -0.5f,  0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f,  0.5f, 0.0f,
                0.5f,  0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
        };
        float[] colors = new float[]{
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
        };
        int[] indices = new int[]{
                0, 1, 3, 3, 1, 2,
        };
        Mesh mesh = new Mesh(positions, colors, indices);

        GameObject gameObject = new GameObject("Trangle");
        gameObject.addComponent(new MeshRenderComponent(mesh));

        addGameObjectToScene(gameObject);
    }



    @Override
    public void update(float dt) {
        Debug.info("FPS" + (1.0f/dt));
    }
}
