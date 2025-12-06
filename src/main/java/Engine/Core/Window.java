package Engine.Core;

import Engine.Core.Listener.KeyListener;
import Engine.Core.Listener.MouseListener;
import Engine.Core.Listener.WindowListener;
import Engine.Core.Utils.Logger;
import Engine.Engine;
import Engine.Scene.SceneManager;
import Engine.Utils.Time;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;
import java.util.concurrent.Callable;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

    private final long windowHandle;
    private static int height;
    private final Callable<Void> resizeFunc;
    private static int width;


    public Window(String title, WindowOptions opts, Callable<Void> resizeFunc) {
        this.resizeFunc = resizeFunc;
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        if (opts.compatibleProfile) {
            glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_COMPAT_PROFILE);
        } else {
            glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
            glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        }

        if (opts.width > 0 && opts.height > 0) {
            this.width = opts.width;
            this.height = opts.height;
        } else {
            glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);
            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            width = vidMode.width();
            height = vidMode.height();
        }

        windowHandle = glfwCreateWindow(width, height, title, NULL, NULL);
        if (windowHandle == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        glfwSetFramebufferSizeCallback(windowHandle, (window, w, h) -> resized(w, h));

        glfwSetErrorCallback((int errorCode, long msgPtr) ->
                Logger.error("Error Code "+errorCode + " " + MemoryUtil.memUTF8(msgPtr))
        );

        setupCallbacks();


        glfwMakeContextCurrent(windowHandle);

        if (opts.fps > 0) {
            glfwSwapInterval(0);
        } else {
            glfwSwapInterval(1);
        }

        glfwShowWindow(windowHandle);

        int[] arrWidth = new int[1];
        int[] arrHeight = new int[1];
        glfwGetFramebufferSize(windowHandle, arrWidth, arrHeight);
        width = arrWidth[0];
        height = arrHeight[0];
    }

    public void cleanUp() {
        glfwFreeCallbacks(windowHandle);
        glfwDestroyWindow(windowHandle);
        glfwTerminate();
        GLFWErrorCallback callback = glfwSetErrorCallback(null);
        if (callback != null) {
            callback.free();
        }
    }

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

    public long getWindowHandle() {
        return windowHandle;
    }

    public boolean isKeyPressed(int keyCode) {
        return glfwGetKey(windowHandle, keyCode) == GLFW_PRESS;
    }

    private void setupCallbacks(){
        glfwSetCursorPosCallback(windowHandle, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(windowHandle, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(windowHandle, MouseListener::mouseScrollCallback);

        glfwSetKeyCallback(windowHandle, KeyListener::keyCallback);
        glfwSetCursorEnterCallback(windowHandle, WindowListener::setCustomerEnterCallback);
    }


    public void pollEvents() {
        glfwPollEvents();
    }

    protected void resized(int width, int height) {
        this.width = width;
        this.height = height;
        try {
            resizeFunc.call();
        } catch (Exception excp) {
            Logger.error("Error calling resize callback: "+ excp);
        }
    }

    public void update() {
        glfwSwapBuffers(windowHandle);
    }

    public boolean windowShouldClose() {
        return glfwWindowShouldClose(windowHandle);
    }

    public static class WindowOptions {
        public boolean compatibleProfile;
        public int fps;
        public int height;
        public int ups = Engine.TARGET_UPS;
        public int width;
    }

//    private String title;
//    private int width, height;
//
//    private static Window window;
//
//    private Callable<Void> resizeWindowFunc;
//
//    private Long glfwWindow;
//
//    SceneManager sceneManager;
//
//    private Window(){
//        this.width = 1920;
//        this.height = 1080;
//        this.title = "Yuba Engine";
//        this.sceneManager= SceneManager.get();
//    }
//
//    public void setWindowTitle(String title){
//        this.title = title;
//    }
//
//    public String getWindowTitle(){
//        return title;
//    }
//
//
//
//    public static Window get() {
//        if(Window.window == null){
//            window = new Window();
//            return window;
//        }
//        return window;
//    }
//
//
//    public void run(){
//        Logger.info("Hello LWJGL " + Version.getVersion() + "!");
//        init();
//        loop();
//
//        //FREE MEMORY
//        glfwFreeCallbacks(glfwWindow);
//        glfwDestroyWindow(glfwWindow);
//
//
//        glfwTerminate();
//    }
//
//
//    private void setupCallbacks(){
//        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
//        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
//        glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);
//
//        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);
//    }
//
//    private void init(){
//        // Setup an error callback. The default implementation
//        // will print the error message in System.err.
//        GLFWErrorCallback.createPrint(System.err).set();
//
//        // Initialize GLFW. Most GLFW functions will not work before doing this.
//        if ( !glfwInit() )
//            throw new IllegalStateException("Unable to initialize GLFW");
//
//        // Configure GLFW
//        glfwDefaultWindowHints(); // optional, the current window hints are already the default
//        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
//        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
//
//        // Create the window
//        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
//        if ( glfwWindow == NULL )
//            throw new RuntimeException("Failed to create the GLFW window");
//
//        // CALLBACKS:
//        setupCallbacks();
//
//
//        // Get the thread stack and push a new frame
//        try ( MemoryStack stack = stackPush() ) {
//            IntBuffer pWidth = stack.mallocInt(1); // int*
//            IntBuffer pHeight = stack.mallocInt(1); // int*
//
//            // Get the window size passed to glfwCreateWindow
//            glfwGetWindowSize(glfwWindow, pWidth, pHeight);
//
//            // Get the resolution of the primary monitor
//            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
//
//            // Center the window
//            assert vidmode != null;
//            glfwSetWindowPos(
//                    glfwWindow,
//                    (vidmode.width() - pWidth.get(0)) / 2,
//                    (vidmode.height() - pHeight.get(0)) / 2
//            );
//        } // the stack frame is popped automatically
//
//        // Make the OpenGL context current
//        glfwMakeContextCurrent(glfwWindow);
//        // Enable v-sync
//        glfwSwapInterval(1);
//
//        // Make the window visible
//        glfwShowWindow(glfwWindow);
//        GL.createCapabilities();
//    }
//
//    private void loop(){
//        float beginTime = Time.getTime();
//        float endTime;
//        float dt = -1.0f;
//
//        SceneManager sceneManager = SceneManager.get();
//
//
//        // Set the clear color
//        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
//
//        // Run the rendering loop until the user has attempted to close
//        // the window or has pressed the ESCAPE key.
//        while ( !glfwWindowShouldClose(glfwWindow) ) {
//            glfwPollEvents();
//
//            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
//
//            if (dt >= 0){
//                sceneManager.UpdateScene(dt);
//            }
//
//            glfwSwapBuffers(glfwWindow); // swap the color buffers
//
//            endTime = Time.getTime();
//            dt = endTime - beginTime;
//            beginTime = endTime;
//        }
//    }
//
//    public static class WindowOptions{
//        public boolean compatibleProfile;
//        public int fps;
//        public int height;
//        private int width;
//        public int ups = Engine.TARGET_UPS;
//    }
}
