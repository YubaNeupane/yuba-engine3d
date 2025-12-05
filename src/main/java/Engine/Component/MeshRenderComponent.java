package Engine.Component;

import Engine.Core.Render.Mesh;
import Engine.Core.Render.Render;

public class MeshRenderComponent extends Component{

    private Mesh mesh;

    public MeshRenderComponent(Mesh mesh){
        this.mesh = mesh;
    }

    public Mesh getMesh(){
        return mesh;
    }

    public void setMesh(Mesh mesh){
        this.mesh = mesh;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void start() {

    }

    @Override
    public void render() {
    }
}
