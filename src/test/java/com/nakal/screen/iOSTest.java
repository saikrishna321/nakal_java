package com.nakal.screen;

import com.nakal.capturescreen.ScreenShooter;
import org.junit.Test;

/**
 * Created by saikrisv on 22/02/16.
 */
public class iOSTest {

    ScreenShooter screenShooter = new ScreenShooter();


    @Test
    public void captureScreenShotFromDevice(){
      screenShooter.screenCapture("iosTest","");
    }
}
