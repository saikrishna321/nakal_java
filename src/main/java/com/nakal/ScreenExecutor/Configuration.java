package com.nakal.ScreenExecutor;

/**
 * Created by hiteshs on 2/20/18.
 */
public class Configuration {
    public static String nakalMode = System.getProperty("nakal.mode", "compare");
    public static String platform = System.getProperty("nakal.platform", "android");
    public static String maskImage = System.getProperty("nakal.maskimage");
    public static String screenshotFolder= System.getProperty("nakal.screenshot.folder");
    public static String baseDirectory= System.getProperty("user.dir");
}
