package com.nakal.screen;

import com.nakal.ScreenExecutor.NakalExecutor;
import com.nakal.capturescreen.ScreenShooter;
import org.junit.Test;

/**
 * Created by saikrisv on 22/02/16.
 */
public class iOSTest {

    ScreenShooter screenShooter = new ScreenShooter();
    NakalExecutor nakalExecutor = new NakalExecutor();

    @Test
    public void captureScreenShotFromDevice(){
        nakalExecutor.nakalExecutorCompareScreenAndCreateDiffImage("ActivityScreen");
    }
}
