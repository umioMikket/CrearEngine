package com.umiomikket.crearengine.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static final DateFormat dateFormat = new SimpleDateFormat("[hh:mm:ss]");

    public static void info(String message) { info(message, false); }

    public static void info(String message, boolean path) {
        System.out.println(String.format(
            "%s %s[INFO]%s %s",
            dateFormat.format(new Date()),
            StringColor.GREEN, StringColor.RESET,
            message
        ));

        if(path) { path(); }
    }

    public static void warning(String message) { warning(message, false); }

    public static void warning(String message, boolean path) {
        System.out.println(String.format(
            "%s %s[WARNING]%s %s",
            dateFormat.format(new Date()),
            StringColor.YELLOW, StringColor.RESET,
            message
        ));

        if(path) { path(); }
    }

    public static void error(String message) { error(message, false); }
    public static void error(String message, boolean end) { error(message, end, false); }

    public static void error(String message, boolean end, boolean path) {
        System.out.println(String.format(
            "%s %s[ERROR]%s %s",
            dateFormat.format(new Date()),
            StringColor.RED, StringColor.RESET,
            message
        ));

        if(path) { path(); }
        if(end) System.exit(-1);
    }

    public static void path() {
        System.out.println(
            StringColor.CYAN + "[DEBUG]" + StringColor.RESET + " The path where there was a complete warning or error:"
        );

        try { throw new RuntimeException(); }
        catch (Exception e) { e.printStackTrace(); }
    }
}
