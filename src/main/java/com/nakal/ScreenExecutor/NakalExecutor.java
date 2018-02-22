package com.nakal.ScreenExecutor;

import com.nakal.capturescreen.ScreenShooter;
import com.nakal.imageutil.ImageUtil;
import com.nakal.utils.ScreenPaths;
import com.nakal.utils.YamlReader;
import org.im4java.core.IM4JavaException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.nakal.ScreenExecutor.Configuration.*;

/**
 * Created by saikrisv on 22/02/16.
 */
public class NakalExecutor {
    public static final String BUILDMODE = "build";
    public static final String COMPAREMODE = "compare";
    public static final String ANDROID="android";
    public static final String CHROME="chrome";
    public static final String IOS="ios";
    public static final String SAFARI="safari";

    private ImageUtil imageUtil;
    private ScreenPaths screenPaths;
    public File file;

    public NakalExecutor(){
        imageUtil = new ImageUtil();
    }

    /**
     * @param baseLineImageName
     * @return false if actual and expected images are not similar and generate a difference Image
     */
    public boolean nakalExecutorNativeCompare(String baseLineImageName)
        throws InterruptedException, IOException, IM4JavaException {
        screenPaths = new ScreenPaths(baseLineImageName);
        if (isBuildMode()) {
            return buildMode(baseLineImageName);
        } else if (isCompareMode()) {
            compareMode(baseLineImageName,null);
        }
        return Boolean.parseBoolean(null);
    }
    /**
     * @param baseLineImageName
     * @param threshold
     * @return false if actual and expected images are not similar and generate a difference Image
     */
    public boolean nakalExecutorNativeCompare(String baseLineImageName, int threshold)
            throws InterruptedException, IOException, IM4JavaException {
        screenPaths = new ScreenPaths(baseLineImageName);
        if (isBuildMode()) {
            return buildMode(baseLineImageName);
        } else if (isCompareMode()) {
            compareMode(baseLineImageName, threshold);
        }
        return false;
    }
    private boolean compareMode(String baseLineImageName,Integer threshold)
            throws InterruptedException, IOException, IM4JavaException {
        String actualImage= screenPaths.getActualImage();
        String maskedActualImage=screenPaths.getMaskedActualImage();
        String maskedRegionActualImage= screenPaths.getMaskedRegionActualImage();
        screenCaptureAndMaskIfExist(baseLineImageName,actualImage,maskedRegionActualImage,maskedActualImage);
        if (threshold != null) {
            if (imageUtil.compareImages(screenPaths.getMaskedExpectedImage(),
                    screenPaths.getMaskedActualImage(), screenPaths.getDiffImage()))
                return true;
        }else{
            if (imageUtil.compareImages(screenPaths.getMaskedExpectedImage(),
                    screenPaths.getMaskedActualImage(), screenPaths.getDiffImage(),threshold))
                return true;
        }
            mergerDiffHorizontal();
        return false;
    }
    private boolean buildMode(String baseLineImageName)
            throws InterruptedException, IOException, IM4JavaException {
        String expectedImage= screenPaths.getExpectedImage();
        String maskedExpectedImage=screenPaths.getMaskedExpectedImage();
        String maskedRegionExpectedImage= screenPaths.getMaskedRegionExpectedImage();
        screenCaptureAndMaskIfExist(baseLineImageName,expectedImage,maskedRegionExpectedImage,maskedExpectedImage);
        return true;
    }

    private void screenCaptureAndMaskIfExist(String baseLineImageName,
                                             String image,String maskedRegionImage, String maskedImage)
            throws IOException, InterruptedException, IM4JavaException {
        captureScreen(baseLineImageName,image);
        if(isMaskAvailable(baseLineImageName)) {
            applyMaskRegion( image, maskedRegionImage,baseLineImageName);
            applyMaskImage(maskedRegionImage, maskedImage);
        }else {
            if(maskImage!= null)
                applyMaskImage(maskedRegionImage, maskedImage);
        }
    }

    private void captureScreen(String fileName, String imagepath){
        new ScreenShooter().screenCapture(fileName,imagepath);
    }

    private void applyMaskRegion(String image,String maskedRegionImage,String baseLineImageName)
            throws InterruptedException, IOException, IM4JavaException {
        imageUtil.maskRegions(image, maskedRegionImage, baseLineImageName);
    }

    private void applyMaskImage(String maskedRegionImage, String maskedImage) throws InterruptedException, IOException, IM4JavaException {
        imageUtil.maskImage(maskedRegionImage, screenPaths.getMaskImage(),
                maskedImage);
    }

    private boolean isMaskAvailable(String baseLineImageName) throws FileNotFoundException {
        return YamlReader.getInstance().checkIfYamlFileExists() && imageUtil.checkIfMaskRegionExists(baseLineImageName);
    }

    private void mergerDiffHorizontal() throws InterruptedException, IOException, IM4JavaException {
        imageUtil.mergeImagesHorizontally(screenPaths.getExpectedImage(),
            screenPaths.getActualImage(), screenPaths.getDiffImage(),
            screenPaths.getMergedDiffImage());
        Thread.sleep(1000);
        file = new File(screenPaths.getDiffImage());
        file.delete();
    }

    public static boolean isBuildMode() {
        return BUILDMODE.equalsIgnoreCase(nakalMode);
    }
    public static boolean isCompareMode(){
        return COMPAREMODE.equalsIgnoreCase(nakalMode);
    }

    public static boolean isAndroid(){
        return ANDROID.equalsIgnoreCase(platform);
    }
    public static boolean isChrome(){
        return CHROME.equalsIgnoreCase(platform);
    }

    public static boolean isiOS(){
        return IOS.equalsIgnoreCase(platform);
    }
    public static boolean isSafari(){
        return SAFARI.equalsIgnoreCase(platform);
    }

}
