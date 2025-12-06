package Engine.ImGui;

import Engine.Core.Window;
import Engine.Scene.Scene;

public interface IGuiInstance {
    void drawGui();

    boolean handleGuiInput(Scene scene, Window window);
}
