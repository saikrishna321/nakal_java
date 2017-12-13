package com.nakal.ScreenExecutor;

import com.nakal.capturescreen.ScreenShooter;
import com.nakal.imageutil.ImageUtil;
import com.nakal.utils.ScreenPaths;
import org.im4java.core.IM4JavaException;

import java.io.File;
import java.io.IOException;

/**
 * Created by saikrisv on 22/02/16.
 */
public class NakalExecutor extends ScreenShooter {

    ImageUtil imageUtil = new ImageUtil();
    ScreenPaths screenPaths = new ScreenPaths();
    public File file;

    /**
     * @param baseLineImageName
     * @return false if actual and expected images are not similar and generate a difference Image
     */
    public boolean nakalExecutorNativeCompare(String baseLineImageName)
        throws InterruptedException, IOException, IM4JavaException {
        return compareTwoImages(baseLineImageName);
    }

    private boolean compareTwoImages(String baseLineImageName)
        throws InterruptedException, IOException, IM4JavaException {
        initialize(baseLineImageName);
        if (System.getenv("NAKAL_MODE").equalsIgnoreCase("build")) {
            return buildMode(baseLineImageName);
        } else if (System.getenv("NAKAL_MODE").equalsIgnoreCase("compare")) {
            try {
                screenCaptureAndMaskRegionsIfPresent(baseLineImageName, baseLineImageName,
                    screenPaths.getActualImage(), screenPaths.getActualMaskedRegionImage(),
                    screenPaths.getMaskedActualImage());

                if (imageUtil.compareImages(screenPaths.getMaskedExpectedImage(),
                        screenPaths.getMaskedActualImage(), screenPaths.getDiffImage())) {
                    return true;
                } else {
                    mergerDiffHorizontal();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IM4JavaException e) {
                e.printStackTrace();
            }
        }
        return Boolean.parseBoolean(null);
    }

    private void screenCaptureAndMaskRegionsIfPresent(String baseLineImageName, String fileName,
        String actualImage, String actualMaskedRegionImage, String maskedActualImage)
        throws InterruptedException, IOException, IM4JavaException {
        screenCapture(fileName, actualImage);
        if (imageUtil.checkIfYamlFileExists()) {
            if (imageUtil.checkIfMaskRegionExists(baseLineImageName)) {
                imageUtil.maskRegions(actualImage, actualMaskedRegionImage, baseLineImageName);
                imageUtil.maskImage(actualMaskedRegionImage, screenPaths.getMaskImage(),
                    maskedActualImage);
            } else {
                imageUtil.maskImage(actualImage, screenPaths.getMaskImage(), maskedActualImage);
            }
        } else {
            if(System.getenv("MASKIMAGE")!= null)
            imageUtil.maskImage(actualImage, screenPaths.getMaskImage(), maskedActualImage);
        }
    }

    private boolean buildMode(String baseLineImageName)
        throws InterruptedException, IOException, IM4JavaException {
        screenCaptureAndMaskRegionsIfPresent(baseLineImageName, baseLineImageName,
            screenPaths.getExpectedImage(), screenPaths.getMaskedRegionExpectedImage(),
            screenPaths.getMaskedExpectedImage());
        return true;
    }

    private void initialize(String baseLineImageName) {
        screenPaths.setExpectedImage(baseLineImageName);
        screenPaths.setMaskedExpectedImage(baseLineImageName);
        screenPaths.setMaskedRegionExpectedImage(baseLineImageName);
        screenPaths.setMaskImage();
        //Actual Params
        screenPaths.setActualImage(baseLineImageName);
        screenPaths.setActualMaskedRegionImage(baseLineImageName);
        screenPaths.setDiffImage(baseLineImageName);
        screenPaths.setMergedDiffImage(baseLineImageName);
        screenPaths.setMaskedActualImage(baseLineImageName);
    }

    public boolean nakalExecutorNativeCompare(String baseLineImageName, int threshold)
        throws InterruptedException, IOException, IM4JavaException {
        initialize(baseLineImageName);
        if (System.getenv("NAKAL_MODE").equalsIgnoreCase("build")) {
            return buildMode(baseLineImageName);
        } else if (System.getenv("NAKAL_MODE").equalsIgnoreCase("compare")) {
            try {
                screenCaptureAndMaskRegionsIfPresent(baseLineImageName,
                     baseLineImageName, screenPaths.getActualImage(),
                    screenPaths.getActualMaskedRegionImage(),
                    screenPaths.getMaskedActualImage());

                if (imageUtil.compareImages(screenPaths.getMaskedExpectedImage(),
                        screenPaths.getMaskedActualImage(), screenPaths.getDiffImage(), threshold)) {
                    file = new File(screenPaths.getDiffImage());
                    file.delete();
                    return true;
                } else {
                    mergerDiffHorizontal();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IM4JavaException e) {
                e.printStackTrace();
            }
        }
        return Boolean.parseBoolean(null);
    }

    private void mergerDiffHorizontal() throws InterruptedException, IOException, IM4JavaException {
        imageUtil.mergeImagesHorizontally(screenPaths.getExpectedImage(),
            screenPaths.getActualImage(), screenPaths.getDiffImage(),
            screenPaths.getMergedDiffImage());
        Thread.sleep(1000);
        file = new File(screenPaths.getDiffImage());
        file.delete();
    }

}
