package com.nakal.devices;

import org.openqa.selenium.WebDriver;

/**
 * Created by saikrisv on 22/02/16.
 */
public interface DeviceInterface {

    public void captureScreenShot(String filePath,String imagePath);

    public void captureScreenShot(WebDriver driver, String imagePath);
}
