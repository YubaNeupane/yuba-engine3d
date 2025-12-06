package Engine;

import Engine.Core.IAppLogic;
import Engine.Core.Render.Render;
import Engine.Core.Window;
import Engine.ImGui.IGuiInstance;
import Engine.Scene.SceneManager;
import Engine.Utils.Time;


public class Engine {
    public static final int TARGET_UPS = 60;
    private final IAppLogic appLogic;
    private final Window window;
    private Render render;
    private boolean running;
    private SceneManager sceneManager;
    private int targetFps;
    private int targetUps;

    public Engine(String windowTitle, Window.WindowOptions windowOptions, IAppLogic appLogic){
        this.window = new Window(windowTitle, windowOptions, ()->{
            resize();
            return null;
        });

        this.targetFps = windowOptions.fps;
        this.targetUps = windowOptions.ups;
        this.appLogic = appLogic;
        render = new Render(window);
        this.sceneManager = SceneManager.get();
    }

    public SceneManager getSceneManager(){
        return sceneManager;
    }

    private void cleanUp(){
        appLogic.cleanUp();
        render.cleanUp();
        if(sceneManager.getCurrentScene() != null){
            sceneManager.getCurrentScene().cleanUp();
        }
        window.cleanUp();
    }

    private void resize() {
        sceneManager.getCurrentScene().resize(Window.getWidth(), Window.getHeight());
        render.resize(Window.getWidth(), Window.getHeight());
    }

    private void run(){
        long initialTime = Time.getCurrentTimeMilis();
        float timeUpdate = 1000.0f/targetUps;
        float timeRender = targetFps > 0 ? 1000.0f / targetFps : 0;
        float deltaUpdate = 0;
        float deltaFps = 0;

        long updateTime = initialTime;
        appLogic.init(window,sceneManager.getCurrentScene(),render);
        IGuiInstance iGuiInstance =sceneManager.getCurrentScene().getGuiInstance();


        while (running && !window.windowShouldClose()){
            window.pollEvents();

            long currentTime = Time.getCurrentTimeMilis();
            deltaUpdate += (currentTime - initialTime) / timeUpdate;
            deltaFps += (currentTime - initialTime) / timeRender;

            if(targetFps <= 0 || deltaFps >= 1){
                window.getMouseInput().input();
                boolean inputConsumed = iGuiInstance != null && iGuiInstance.handleGuiInput(sceneManager.getCurrentScene(), window);
                appLogic.input(window, sceneManager.getCurrentScene(), currentTime - initialTime,inputConsumed);
            }

            if (deltaUpdate >= 1){
                long diffTimeMillis = currentTime - updateTime;
                sceneManager.getCurrentScene().update(diffTimeMillis);
                appLogic.update(window, sceneManager.getCurrentScene(), diffTimeMillis);
                updateTime = currentTime;
                deltaUpdate--;
            }

            if(targetFps <= 0 || deltaFps >= 1){
                render.render(window, sceneManager.getCurrentScene());
                deltaFps--;
                window.update();
            }
            initialTime = currentTime;
        }

        cleanUp();
    }

    public void start(){
        running = true;
        run();
    }

    public void stop(){
        running = false;
    }


}
