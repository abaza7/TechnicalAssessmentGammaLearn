package com.swiftAssess.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static String snap(WebDriver driver, String namePrefix) {
        try {
            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String ts = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
            String path = "target/screenshots/" + namePrefix + "_" + ts + ".png";
            File dest = new File(path);
            dest.getParentFile().mkdirs();
            FileHandler.copy(src, dest);
            return path;
        } catch (Exception e) { return null; }
    }
}
