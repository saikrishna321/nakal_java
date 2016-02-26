package com.nakal.screen;

import com.nakal.ScreenExecutor.NakalExecutor;
import com.nakal.devices.AndroidDeviceScreen;
import com.nakal.imageutil.ImageUtil;
import org.im4java.core.IM4JavaException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by saikrisv on 22/02/16.
 */
public class AndroidTest {

    AndroidDeviceScreen devices = new AndroidDeviceScreen();
    ImageUtil imageUtil = new ImageUtil();
    NakalExecutor nakalExecutor = new NakalExecutor();

    @Test
    public void testDeviceConnected() {
        Assert.assertEquals(devices.checkIfDevicesAreConnected(), true);
    }


    @Test
    public void verifyImagesAreSimilar() throws InterruptedException, IOException, IM4JavaException {
   /*     boolean imagePixels = imageUtil.compareImages(System.getProperty("user.dir") + "/testImages/ActivityScreen.png",
                System.getProperty("user.dir") + "/testImages/ActivityScreen.png");
        Assert.assertTrue(imagePixels);*/
           Assert.assertTrue(nakalExecutor.nakalExecutorCompareScreenAndCreateDiffImage("ActivityScreen"));

    }

    @Test
    public void verifyImagesAreNotSimilarAndCreatDiffImage() throws InterruptedException, IOException, IM4JavaException {
        boolean imagePixels = imageUtil.compareImages(System.getProperty("user.dir") + "/testImages/ActivityScreen.png",
                System.getProperty("user.dir") + "/testImages/ActivityScreen1.png",
                System.getProperty("user.dir") + "/testImages/ActivityDifferentImage.png");
        Assert.assertFalse(imagePixels);
    }

    @Test
    public void mergeImagesHorizontally() throws InterruptedException, IOException, IM4JavaException {
        imageUtil.mergeImagesHorizontally(System.getProperty("user.dir") + "/testImages/ActivityScreen.png",
                System.getProperty("user.dir") + "/testImages/ActivityScreen1.png",
                System.getProperty("user.dir") + "/testImages/ActivityDifferentImage.png",
                System.getProperty("user.dir") + "/testImages/MergedImage.png");
    }


    @Test
    public void verifyThresholdDifference() throws InterruptedException, IOException, IM4JavaException {
        imageUtil.compareImagesWithPixelDifferenceInPercentage(System.getProperty("user.dir") + "/testImages/googleActual.png",
                System.getProperty("user.dir") + "/testImages/googleExpected.png", 20);
    }

    @Test
    public void maskImageVerification() throws InterruptedException, IOException, IM4JavaException {
        imageUtil.maskImage("/Users/saikrisv/Desktop/ActivityScreenReference.png",
                "/Users/saikrisv/Desktop/ActivityScreenMaskImage.png",
                "/Users/saikrisv/Desktop/masked.png");
    }

}
