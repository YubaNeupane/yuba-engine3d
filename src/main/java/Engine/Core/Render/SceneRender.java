package Engine.Core.Render;

import Engine.Component.MeshRenderComponent;
import Engine.Core.Utils.Logger;
import Engine.Scene.Scene;
import Engine.Utils.Debug;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

import static org.lwjgl.opengl.GL30.*;

public class SceneRender {

    private ShaderProgram shaderProgram;

    public SceneRender() {
        List<ShaderProgram.ShaderModuleData> shaderModuleDataList = new ArrayList<>();
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER));
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER));
        shaderProgram = new ShaderProgram(shaderModuleDataList);
    }

    public void cleanUp(){
        shaderProgram.cleanUp();
    }

    public void render(Scene scene){
        shaderProgram.bind();


        scene.getGameObjects().forEach(gameObject -> {
            if (gameObject.getComponent(MeshRenderComponent.class) != null){
                Mesh mesh = gameObject.getComponent(MeshRenderComponent.class).getMesh();
                glBindVertexArray(mesh.getVaoId());
                glDrawArrays(GL_TRIANGLES, 0, mesh.getNumVertices());
            }
        });

        glBindVertexArray(0);

        shaderProgram.unbind();
    }



}
