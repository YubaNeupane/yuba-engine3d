package Engine.Component;

import Engine.GameObject.GameObject;

public abstract class Component {

    public GameObject gameObject = null;

    public abstract void update(float dt);

    public abstract void start();

    public abstract void render();
}
