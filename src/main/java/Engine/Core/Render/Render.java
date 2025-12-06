package Engine.Core.Render;

import Engine.Core.GUI.GuiRender;
import Engine.Core.Window;
import Engine.Scene.Scene;
import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL14.GL_FUNC_ADD;
import static org.lwjgl.opengl.GL14.glBlendEquation;

public class Render {

    private SceneRender sceneRender;
    private GuiRender guiRender;

    public Render(Window window){
        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);

        sceneRender = new SceneRender();
        guiRender = new GuiRender(window);

    }

    public void cleanUp(){
        sceneRender.cleanUp();
        guiRender.cleanup();
    }

    public void render(Window window, Scene scene){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glViewport(0,0, Window.getWidth(), Window.getHeight());

        sceneRender.render(scene);
        guiRender.render(scene);
    }

    public void resize(int width, int height) {
        guiRender.resize(width, height);
    }
}
