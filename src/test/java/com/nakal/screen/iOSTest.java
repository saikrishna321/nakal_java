package com.nakal.screen;

import com.nakal.ScreenExecutor.NakalExecutor;
import org.im4java.core.IM4JavaException;
import org.junit.Test;
import org.openqa.selenium.remote.CapabilityType;

import java.io.IOException;

/**
 * Created by saikrisv on 22/02/16.
 */
public class iOSTest {

    NakalExecutor nakalExecutor = new NakalExecutor();

    @Test
    public void captureScreenShotFromDevice()
        throws InterruptedException, IOException, IM4JavaException {
        nakalExecutor.nakalExecutorNativeCompare("ActivityScreen");
    }
}






