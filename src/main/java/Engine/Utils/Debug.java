package Engine.Utils;

import Engine.Core.Utils.Logger;

public class Debug {

    private static final String RESET = "\u001B[0m";

    // Level colors
    private static final String RED    = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN  = "\u001B[32m";
    private static final String BLUE   = "\u001B[34m";

    public enum Level {
        ERROR, WARN, INFO, DEBUG
    }

    private static Logger.Level currentLevel = Logger.Level.DEBUG;

    public static void setLevel(Logger.Level level) {
        currentLevel = level;
    }

    private static void Log(Logger.Level level, String message) {
        if (level.ordinal() > currentLevel.ordinal()) return;

        String color = switch (level) {
            case ERROR -> RED;
            case WARN  -> YELLOW;
            case INFO  -> GREEN;
            case DEBUG -> BLUE;
        };

        System.out.println(
                color + "[" + level + "] " + RESET + ": " + message + RESET
        );
    }

    public static void error(String msg) { Log(Logger.Level.ERROR, msg); }
    public static void warn(String msg)  { Log(Logger.Level.WARN, msg); }
    public static void info(String msg)  { Log(Logger.Level.INFO, msg); }
    public static void debug(String msg) { Log(Logger.Level.DEBUG, msg); }
}
