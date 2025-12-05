package Engine.Core.Render;

import Engine.Component.MeshRenderComponent;
import Engine.Scene.Scene;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL30.*;

public class SceneRender {

    private ShaderProgram shaderProgram;
    private UniformsMap uniformsMap;

    public SceneRender() {
        List<ShaderProgram.ShaderModuleData> shaderModuleDataList = new ArrayList<>();
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER));
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER));
        shaderProgram = new ShaderProgram(shaderModuleDataList);
        createUniforms();
    }

    private void createUniforms() {
        uniformsMap = new UniformsMap(shaderProgram.getProgramId());
        uniformsMap.createUniform("projectionMatrix");
        uniformsMap.createUniform("modelMatrix");
    }

    public void cleanUp(){
        shaderProgram.cleanUp();
    }

    public void render(Scene scene){
        shaderProgram.bind();

        uniformsMap.setUniform("projectionMatrix", scene.getProjection().getProjMatrix());

        scene.getGameObjects().forEach(gameObject -> {
            if (gameObject.getComponent(MeshRenderComponent.class) != null){
                Mesh mesh = gameObject.getComponent(MeshRenderComponent.class).getMesh();
                glBindVertexArray(mesh.getVaoId());
                uniformsMap.setUniform("modelMatrix", gameObject.getModelMatrix());
                glDrawElements(GL_TRIANGLES, mesh.getNumVertices(), GL_UNSIGNED_INT, 0);
            }
        });

        glBindVertexArray(0);

        shaderProgram.unbind();
    }



}
