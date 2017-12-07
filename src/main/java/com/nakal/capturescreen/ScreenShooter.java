package com.nakal.capturescreen;

import com.nakal.devices.DeviceInterface;
import com.nakal.devices.ViewFactory;

/**
 * Created by saikrisv on 22/02/16.
 */
public class ScreenShooter {
    private ViewFactory viewFactory = new ViewFactory();

    public void screenCapture(String fileName,String imagePath) {
        DeviceInterface runnerInfo = viewFactory.getMobilePlatform(System.getenv("Platform"));
        runnerInfo.captureScreenShot(fileName,imagePath);
    }
}
