package util;

public class Logger {

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";

    public static void info(String message) {
        System.out.println(GREEN + "[INFO] " + RESET + message);
    }

    public static void error(String message) {
        System.out.println(RED + "[ERROR] " + RESET + message);
    }

    public static void warn(String message) {
        System.out.println(YELLOW + "[WARN] " + RESET + message);
    }

    public static void header(String message) {
        System.out.println(CYAN + "\n=== " + message.toUpperCase() + " ===" + RESET);
    }

    public static void prompt(String message) {
        System.out.print(BLUE + message + RESET + " ");
    }
}
