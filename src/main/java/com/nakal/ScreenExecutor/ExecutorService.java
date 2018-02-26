package com.nakal.ScreenExecutor;

import com.nakal.capturescreen.ScreenShooter;
import com.nakal.imageutil.ImageUtil;
import com.nakal.utils.ScreenPaths;
import org.im4java.core.IM4JavaException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static com.nakal.ScreenExecutor.NakalAttributeValidator.isFullScreen;
import static com.nakal.ScreenExecutor.NakalAttributeValidator.isIgnoreRegion;

/**
 * Created by hiteshs on 2/25/18.
 */
public class ExecutorService {
    private ScreenPaths screenPaths;
    private File file;
    private ImageUtil imageUtil;
    public ExecutorService(){
        imageUtil = new ImageUtil();
    }

    public boolean compareMode(String baseLineImageName,int threshold)
            throws InterruptedException, IOException, IM4JavaException {
        screenPaths = new ScreenPaths(baseLineImageName);
        String actualImage= screenPaths.getActualImage();
        String expectedImage=screenPaths.getExpectedImage();
        String diffImage=screenPaths.getDiffImage();
        screenCaptureAndMaskIfExist(baseLineImageName,actualImage);
        if (threshold >0) {
            if (imageUtil.compareImages(expectedImage,
                    actualImage,diffImage ))
                return true;
        }else{
            if (imageUtil.compareImages(expectedImage,
                    actualImage, diffImage,threshold))
                return true;
        }
        mergerDiffHorizontal(expectedImage,actualImage,diffImage);
        return false;
    }
    public boolean buildMode(String baseLineImageName)
            throws InterruptedException, IOException, IM4JavaException {
        screenPaths = new ScreenPaths(baseLineImageName);
        String expectedImage= screenPaths.getExpectedImage();
        screenCaptureAndMaskIfExist(baseLineImageName,expectedImage);
        return checkIfScreenshotsCaptured(expectedImage);
    }
    private boolean checkIfScreenshotsCaptured(String image)
            throws FileNotFoundException {
        File expectedImage= new File(image);
        return expectedImage.exists();

    }
    private void screenCaptureAndMaskIfExist(String baseLineImageName,
                                             String image)
            throws IOException, InterruptedException, IM4JavaException {
        captureScreen(baseLineImageName,image);
        applyMaskIfPresent(baseLineImageName,image);

    }
    private void applyMaskIfPresent(String baseLineImageName,String image)
            throws IOException, InterruptedException, IM4JavaException {
        if(isIgnoreRegion(baseLineImageName))
            ignoreRegionOnApp(image,baseLineImageName);
        if(!isFullScreen())
            applyMaskImage(image);
    }
    private void ignoreRegionOnApp(String image, String baseLineImageName) throws InterruptedException, IOException, IM4JavaException {
        imageUtil.maskRegions(image,baseLineImageName);
    }

    private void captureScreen(String fileName, String imagepath){
        new ScreenShooter().screenCapture(fileName,imagepath);
    }
    private void applyMaskImage(String maskedRegionImage) throws InterruptedException, IOException, IM4JavaException {
        imageUtil.maskImage1(maskedRegionImage, screenPaths.getMaskImage());
    }
    private void mergerDiffHorizontal(String expectedImage,String actualImage,String diffImage)
            throws InterruptedException, IOException, IM4JavaException {
        imageUtil.mergeImagesHorizontally(expectedImage,
                actualImage, diffImage,
                screenPaths.getMergedDiffImage());
        Thread.sleep(1000);
        file = new File(screenPaths.getDiffImage());
        file.delete();
    }
}
