package com.nakal.screen;

import com.nakal.imageutil.ImageUtil;
import org.im4java.core.IM4JavaException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by hiteshs on 3/2/18.
 */
public class NakalTest {

    ImageUtil imageUtil = new ImageUtil();

    @Test
    public void verifyImagesAreSimilar() throws InterruptedException, IOException, IM4JavaException {
        boolean imagePixels = imageUtil.compareImages(System.getProperty("user.dir") + "/testImages/ActivityScreen.png",
                System.getProperty("user.dir") + "/testImages/ActivityScreen.png");
        Assert.assertTrue(imagePixels);

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
        imageUtil.compareImages(System.getProperty("user.dir") + "/testImages/googleActual.png",
                System.getProperty("user.dir") + "/testImages/googleExpected.png",
                System.getProperty("user.dir") + "/testImages/percentageDiff.png",2);
    }
}
