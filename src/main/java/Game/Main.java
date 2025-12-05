package Game;

import Engine.Core.IAppLogic;
import Engine.Core.Listener.KeyListener;
import Engine.Core.Render.Mesh;
import Engine.Core.Render.Render;
import Engine.Core.Window;
import Engine.Engine;
import Engine.GameObject.GameObject;
import Engine.Scene.Scene;
import Engine.Utils.Debug;
import Game.Scene.TestScene;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_0;

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

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis) {
    }

    @Override
    public void update(Window window, Scene scene, long diffTimeMillis) {
    }
}

