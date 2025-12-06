package Engine.GameObject;

import Engine.Component.MeshRenderComponent;
import Engine.Core.Render.Mesh;
import Engine.Core.Render.Model;

public class CustomGameObject extends GameObject{


    public CustomGameObject(String name, Model model) {
        super(name);
        super.models.add(model);
    }

    @Override
    protected void init() {

    }
}
