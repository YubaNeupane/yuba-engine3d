package Engine.GameObject;

import Engine.Component.Component;
import Engine.Component.TransformComponent;
import Engine.Core.Utils.Logger;

import java.util.*;

public abstract class GameObject {
    private String name;
    public List<Component> components;

    public GameObject(String name){
        this.name = name;
        this.components = new ArrayList<>();
        addComponent(new TransformComponent());
    }


    public String getName(){
        return name;
    }

    protected abstract void init();

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
        c.gameObject = this;
        this.components.add(c);
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
