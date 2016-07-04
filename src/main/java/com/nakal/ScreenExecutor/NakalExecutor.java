package com.nakal.ScreenExecutor;

import com.nakal.capturescreen.ScreenShooter;
import com.nakal.devices.WebScreen;
import com.nakal.imageutil.ImageUtil;
import com.nakal.utils.NativeCompare;
import org.im4java.core.IM4JavaException;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by saikrisv on 22/02/16.
 */
public class NakalExecutor extends ScreenShooter {

    ImageUtil imageUtil = new ImageUtil();
    WebScreen webScreen = new WebScreen();
    NativeCompare nativeCompare = new NativeCompare();
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
                screenCaptureAndMaskRegionsIfPresent(baseLineImageName,
                    "actual_" + baseLineImageName, nativeCompare.getActualImage(),
                    nativeCompare.getActualMaskedRegionImage(),
                    nativeCompare.getMaskedActualImage());

                if (imageUtil.compareImages(nativeCompare.getMaskedExpectedImage(),
                    nativeCompare.getMaskedActualImage(), nativeCompare.getDiffImage()) == true) {
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

        if (imageUtil.checkIfMaskRegionExists(baseLineImageName)) {
            imageUtil.maskRegions(actualImage, actualMaskedRegionImage, baseLineImageName);
            imageUtil.maskImage(actualMaskedRegionImage, nativeCompare.getMaskImage(),
                maskedActualImage);
        } else {
            imageUtil.maskImage(actualImage, nativeCompare.getMaskImage(), maskedActualImage);
        }
    }

    private boolean buildMode(String baseLineImageName)
        throws InterruptedException, IOException, IM4JavaException {
        screenCaptureAndMaskRegionsIfPresent(baseLineImageName, baseLineImageName,
            nativeCompare.getExpectedImage(), nativeCompare.getMaskedRegionExpectedImage(),
            nativeCompare.getMaskedExpectedImage());
        return true;
    }

    private void initialize(String baseLineImageName) {
        nativeCompare.setExpectedImage(baseLineImageName);
        nativeCompare.setMaskedExpectedImage(baseLineImageName);
        nativeCompare.setMaskedRegionExpectedImage(baseLineImageName);
        nativeCompare.setMaskImage();
        //Actual Params
        nativeCompare.setActualImage(baseLineImageName);
        nativeCompare.setActualMaskedRegionImage(baseLineImageName);
        nativeCompare.setDiffImage(baseLineImageName);
        nativeCompare.setMergedDiffImage(baseLineImageName);
        nativeCompare.setMaskedActualImage(baseLineImageName);
    }


    public boolean nakalExecutorWebCompare(WebDriver driver, String baseLineImageName) {
        String expectedImage =
            System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") + "/" + System
                .getenv("APP") + "/baseline_images/" + baseLineImageName + ".png";
        if (System.getenv("NAKAL_MODE").equalsIgnoreCase("build")) {
            webScreen.captureScreenShot(driver, expectedImage);
            return true;
        } else if (System.getenv("NAKAL_MODE").equalsIgnoreCase("compare")) {
            try {
                String actualImage =
                    System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") + "/" + System
                        .getenv("APP") + "/actual_images/actual_" + baseLineImageName + ".png";
                String diffImage =
                    System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") + "/" + System
                        .getenv("APP") + "/actual_images/diff_" + baseLineImageName + ".png";
                String mergedDiffImage =
                    System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") + "/" + System
                        .getenv("APP") + "/actual_images/difference_" + baseLineImageName + ".png";
                webScreen.captureScreenShot(driver, actualImage);
                if (imageUtil.compareImages(expectedImage, actualImage, diffImage) == true) {
                    return true;
                } else {
                    imageUtil.mergeImagesHorizontally(expectedImage, actualImage, diffImage,
                        mergedDiffImage);
                    file = new File(diffImage);
                    file.delete();
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


    public boolean nakalExecutorWebCompare(WebDriver driver, String baseLineImageName,
        int threshold) {
        String expectedImage =
            System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") + "/" + System
                .getenv("APP") + "/baseline_images/" + baseLineImageName + ".png";
        if (System.getenv("NAKAL_MODE").equalsIgnoreCase("build")) {
            webScreen.captureScreenShot(driver, expectedImage);
            return true;
        } else if (System.getenv("NAKAL_MODE").equalsIgnoreCase("compare")) {
            try {
                String actualImage =
                    System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") + "/" + System
                        .getenv("APP") + "/actual_images/actual_" + baseLineImageName + ".png";
                String diffImage =
                    System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") + "/" + System
                        .getenv("APP") + "/actual_images/diff_" + baseLineImageName + ".png";
                String mergedDiffImage =
                    System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") + "/" + System
                        .getenv("APP") + "/actual_images/difference_" + baseLineImageName + ".png";
                webScreen.captureScreenShot(driver, actualImage);
                if (imageUtil.compareImages(expectedImage, actualImage, diffImage, threshold)
                    == true) {
                    file = new File(diffImage);
                    file.delete();
                    return true;
                } else {
                    imageUtil.mergeImagesHorizontally(expectedImage, actualImage, diffImage,
                        mergedDiffImage);
                    file = new File(diffImage);
                    file.delete();
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


    public boolean nakalExecutorNativeCompare(String baseLineImageName, int threshold)
        throws InterruptedException, IOException, IM4JavaException {
        initialize(baseLineImageName);
        if (System.getenv("NAKAL_MODE").equalsIgnoreCase("build")) {
            return buildMode(baseLineImageName);
        } else if (System.getenv("NAKAL_MODE").equalsIgnoreCase("compare")) {
            try {
                screenCaptureAndMaskRegionsIfPresent(baseLineImageName,
                    "actual_" + baseLineImageName, nativeCompare.getActualImage(),
                    nativeCompare.getActualMaskedRegionImage(),
                    nativeCompare.getMaskedActualImage());

                if (imageUtil
                    .compareImages(nativeCompare.getMaskedExpectedImage(),
                        nativeCompare.getMaskedActualImage(), nativeCompare.getDiffImage(), threshold)
                    == true) {
                    file = new File(nativeCompare.getDiffImage());
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
        imageUtil.mergeImagesHorizontally(nativeCompare.getExpectedImage(),
            nativeCompare.getActualImage(), nativeCompare.getDiffImage(),
            nativeCompare.getMergedDiffImage());
        file = new File(nativeCompare.getDiffImage());
        file.delete();
    }

}
