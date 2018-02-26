package com.nakal.capturescreen;

import static com.nakal.ScreenExecutor.Configuration.platform;
import com.nakal.devices.DeviceInterface;
import com.nakal.devices.ViewFactory;

/**
 * Created by saikrisv on 22/02/16.
 */
public class ScreenShooter {
    private ViewFactory viewFactory = new ViewFactory();

    public void screenCapture(String imagePath) {
        DeviceInterface runnerInfo = viewFactory.getMobilePlatform(platform);
        runnerInfo.captureScreenShot(imagePath);
    }
}
