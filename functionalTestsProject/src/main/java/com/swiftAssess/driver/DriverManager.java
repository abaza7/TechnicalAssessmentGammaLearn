package com.swiftAssess.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    private static final ThreadLocal<WebDriver> TL = new ThreadLocal<>();

    public static void init(boolean mobileEmulation) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--start-maximized");
        if (System.getProperty("headless","false").equals("true")) {
            opts.addArguments("--headless=new");
        }
        if (mobileEmulation) {
            java.util.Map<String, Object> mobileEmulationConfig = new java.util.HashMap<>();
            mobileEmulationConfig.put("deviceName", "iPhone 14 Pro Max");
            opts.setExperimentalOption("mobileEmulation", mobileEmulationConfig);
        }
        TL.set(new ChromeDriver(opts));
    }

    public static WebDriver get() { return TL.get(); }

    public static void quit() {
        if (TL.get()!=null) { TL.get().quit(); TL.remove(); }
    }
}
