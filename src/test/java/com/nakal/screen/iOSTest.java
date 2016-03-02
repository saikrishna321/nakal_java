package com.nakal.screen;

import com.nakal.ScreenExecutor.NakalExecutor;
import org.junit.Test;

/**
 * Created by saikrisv on 22/02/16.
 */
public class iOSTest {

    NakalExecutor nakalExecutor = new NakalExecutor();

    @Test
    public void captureScreenShotFromDevice(){
        nakalExecutor.nakalExecutorNativeCompare("ActivityScreen");
    }
}
