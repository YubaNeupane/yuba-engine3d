package Engine.Core;

import Engine.Core.Render.Render;
import Engine.Scene.Scene;

public interface IAppLogic {

    void cleanUp();

    void init(Window window, Scene scene, Render render);

    void input(Window window, Scene scene, long diffTimeMillis, boolean inputConsumed);

    void update(Window window, Scene scene, long diffTimeMillis);

}
