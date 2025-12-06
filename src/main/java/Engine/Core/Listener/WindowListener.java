package Engine.Core.Listener;

public class WindowListener {
    private static WindowListener instance;

    boolean inWindow = false;

    private WindowListener(){

    }

    public static WindowListener get(){
        if(WindowListener.instance == null){
            instance = new WindowListener();
        }
        return instance;
    }

    public static void setCustomerEnterCallback(Long window, boolean entered){
        get().inWindow = entered;
    }


}
