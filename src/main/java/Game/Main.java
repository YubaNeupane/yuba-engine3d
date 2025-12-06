package Game;

import Engine.Core.IAppLogic;
import Engine.Core.Listener.KeyListener;
import Engine.Core.Listener.MouseListener;
import Engine.Core.Render.Mesh;
import Engine.Core.Render.Render;
import Engine.Core.Window;
import Engine.Engine;
import Engine.GameObject.GameObject;
import Engine.Scene.Camera;
import Engine.Scene.Scene;
import Engine.Utils.Debug;
import Game.Scene.TestScene;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class Main implements IAppLogic {

    public static void main(String[] args) {
        Main main = new Main();
        Engine gameEngine = new Engine("Yuba Engine", new Window.WindowOptions(), main);

        gameEngine.getSceneManager().addScene("Test", new TestScene());


        gameEngine.start();
    }

    @Override
    public void cleanUp() {

    }

    @Override
    public void init(Window window, Scene scene, Render render) {
        scene.init();

    }

    private static final float MOUSE_SENSITIVITY = 0.1f;
    private static final float MOVEMENT_SPEED = 0.005f;

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis) {
        float move = diffTimeMillis * MOVEMENT_SPEED;
        Camera camera = scene.getCamera();
        if (KeyListener.get().isKeyPressed(GLFW_KEY_W)) {
            camera.moveForward(move);
        } else if (KeyListener.get().isKeyPressed(GLFW_KEY_S)) {
            camera.moveBackwards(move);
        }
        if (KeyListener.get().isKeyPressed(GLFW_KEY_A)) {
            camera.moveLeft(move);
        } else if (KeyListener.get().isKeyPressed(GLFW_KEY_D)) {
            camera.moveRight(move);
        }
        if (KeyListener.get().isKeyPressed(GLFW_KEY_UP)) {
            camera.moveUp(move);
        } else if (KeyListener.get().isKeyPressed(GLFW_KEY_DOWN)) {
            camera.moveDown(move);
        }

        if (MouseListener.get().isMouseButtonDown(GLFW_MOUSE_BUTTON_1)) {
            Vector2f displVec = MouseListener.get().getDisplVec();
            camera.addRotation((float) Math.toRadians(-displVec.x * MOUSE_SENSITIVITY),
                    (float) Math.toRadians(-displVec.y * MOUSE_SENSITIVITY));
        }


    }

    @Override
    public void update(Window window, Scene scene, long diffTimeMillis) {

    }
}

