package Engine.GameObject;

import Engine.Component.Component;
import Engine.Core.Render.Mesh;
import Engine.Core.Utils.Logger;

import java.util.*;

public class GameObject {
    private String name;
    public List<Component> components;

//    private Map<String, Mesh> meshMap;
//
    public GameObject(String name){
        this.name = name;
        this.components = new ArrayList<>();
    }
//
//    public void addMesh(String meshId, Mesh mesh){
//        meshMap.put(meshId, mesh);
//    }
//
//    public Map<String, Mesh> getMeshMap(){
//        return meshMap;
//    }

    public String getName(){
        return name;
    }

    public <T extends Component> T getComponent(Class <T> componentClass){
        for (Component c: components){
            if(componentClass.isAssignableFrom(c.getClass())){
                try{
                    return componentClass.cast(c);
                }
                catch (ClassCastException e){
                    Logger.error("GameObject - " + name + " Casting component");
                    Logger.error(e.getMessage());
                }
            }
        }
        return null;
    }

    public <T extends Component> void removeComponent(Class <T> componentClass){
        Iterator<Component> i = components.iterator();

        while (i.hasNext()){
            Component c = i.next();
            if(componentClass.isAssignableFrom(c.getClass())){
               i.remove();
               break;
            }
        }
    }

    public void addComponent(Component c){
        this.components.add(c);
        c.gameObject = this;
    }

    public void update(float dt){
        for (Component component : components) {
            component.update(dt);
        }
    }

    public void start(){
        for (Component component : components) {
            component.start();
        }
    }

}
