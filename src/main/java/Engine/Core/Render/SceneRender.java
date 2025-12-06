package Engine.Core.Render;

import Engine.Component.MeshRenderComponent;
import Engine.Core.Render.Texture.Texture;
import Engine.Core.Render.Texture.TextureCache;
import Engine.Entity.Entity;
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
        uniformsMap.createUniform("viewMatrix");
        uniformsMap.createUniform("txtSampler");
        uniformsMap.createUniform("material.diffuse");

    }

    public void cleanUp(){
        shaderProgram.cleanUp();
    }

    public void render(Scene scene){
        shaderProgram.bind();

        uniformsMap.setUniform("projectionMatrix", scene.getProjection().getProjMatrix());
        uniformsMap.setUniform("viewMatrix", scene.getCamera().getViewMatrix());


        uniformsMap.setUniform("txtSampler", 0);

        TextureCache textureCache = scene.getTextureCache();

        scene.getGameObjects().forEach(gameObject -> {
            for(Model model: gameObject.models){
                List<Entity> entities = model.getEntitiesList();
                for(Material material: model.getMaterialList()){
                    uniformsMap.setUniform("material.diffuse", material.getDiffuseColor());
                    Texture texture = textureCache.getTexture(material.getTexturePath());
                    glActiveTexture(GL_TEXTURE0);
                    texture.bind();

                    for (Mesh mesh: material.getMeshList()){
                        glBindVertexArray(mesh.getVaoId());

                        uniformsMap.setUniform("modelMatrix", gameObject.getModelMatrix());
                        glDrawElements(GL_TRIANGLES, mesh.getNumVertices(), GL_UNSIGNED_INT, 0);
                    }
                }

//                if (gameObject.getComponent(MeshRenderComponent.class) != null){
//                    Mesh mesh = gameObject.getComponent(MeshRenderComponent.class).getMesh();
//
//                }
            }
        });



        glBindVertexArray(0);

        shaderProgram.unbind();
    }



}
