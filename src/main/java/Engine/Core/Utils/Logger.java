package Engine.Core.Utils;

public class Logger {
    // Unique engine color
    private static final String ENGINE_COLOR = "\u001B[96;1m";  // Bright Cyan Bold
    private static final String RESET = "\u001B[0m";

    // Level colors
    private static final String RED    = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN  = "\u001B[32m";
    private static final String BLUE   = "\u001B[34m";

    private static final String ENGINE_TAG = "Yuba3D";

    public enum Level {
        ERROR, WARN, INFO, DEBUG
    }

    private static Level currentLevel = Level.DEBUG;

    public static void setLevel(Level level) {
        currentLevel = level;
    }

    private static void engineLog(Level level, String message) {
        if (level.ordinal() > currentLevel.ordinal()) return;

        String color = switch (level) {
            case ERROR -> RED;
            case WARN  -> YELLOW;
            case INFO  -> GREEN;
            case DEBUG -> BLUE;
        };

        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StackTraceElement caller = stackTraceElements[3];

        String callers = BLUE + "- [Function:" + caller.getMethodName().split("\\$")[1] + " Class:" + caller.getFileName()+"] - ";
        System.out.println(
                ENGINE_COLOR + "[" + ENGINE_TAG + "] " + callers + RESET +
                        color + level + ": " + message + RESET
        );
    }

    public static void error(String msg) { engineLog(Level.ERROR, msg); }
    public static void warn(String msg)  { engineLog(Level.WARN, msg); }
    public static void info(String msg)  { engineLog(Level.INFO, msg); }
    public static void debug(String msg) { engineLog(Level.DEBUG, msg); }
}
