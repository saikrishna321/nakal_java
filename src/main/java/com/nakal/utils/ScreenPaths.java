package com.nakal.utils;


import com.nakal.ScreenExecutor.Configuration;

import java.io.File;

import static com.nakal.ScreenExecutor.Configuration.baseDirectory;
import static com.nakal.ScreenExecutor.Configuration.screenshotFolder;
import static com.nakal.ScreenExecutor.Configuration.platform;

/**
 * Created by saikrisv on 04/07/16.
 */
public class ScreenPaths {

    private String customPath;
    private String expectedImage;
    private String maskImage;
    private String actualImage;

    private String mergedDiffImage;
    private String diffImage;


    public ScreenPaths(String baseLineImageName) {
           if (screenshotFolder!=null) {
               customPath = screenshotFolder
                       + File.separator + platform;
           } else {
               customPath = platform;
           }
           init(baseLineImageName);
    }


    private void init(String baseLineImageName){

        setActualImage(baseLineImageName);
        setExpectedImage(baseLineImageName);
        setDiffImage(baseLineImageName);
        setMaskImage();
        setMergedDiffImage(baseLineImageName);
    }

    private void setExpectedImage(String baseLineImageName) {
        this.expectedImage =
                getBasePathForBaseLine(baseLineImageName) + baseLineImageName
                        + ".png";
    }

    private void setMaskImage() {
        this.maskImage =
                baseDirectory + File.separator + platform + "/mask_images/"
                        + Configuration.maskImage + ".png";
    }

    private void setMergedDiffImage(String baseLineImageName) {
        this.mergedDiffImage =
                getBasePathForTargetActualImage(baseLineImageName)+ "difference_"
                        + baseLineImageName + ".png";

    }

    private void setDiffImage(String baseLineImageName) {
        this.diffImage =
                getBasePathForTargetActualImage(baseLineImageName)+ "diff_"
                        + baseLineImageName + ".png";

    }

    private void setActualImage(String baseLineImageName) {
        this.actualImage =
                getBasePathForTargetActualImage(baseLineImageName)
                        + baseLineImageName + ".png";
    }


    private String getBasePathForTargetActualImage(String baseLineImageName){
        return baseDirectory + "/target/" + customPath
                + "/actual_images/" + baseLineImageName + File.separator;
    }

    private String getBasePathForBaseLine(String baseLineImageName){
        return baseDirectory + File.separator + customPath
                + "/baseline_images/" + baseLineImageName + File.separator;
    }

    public String getCustomPath() {
        return customPath;
    }

    public String getExpectedImage() {
        return expectedImage;
    }

    public String getMaskImage() {
        return maskImage;
    }

    public String getActualImage() {
        return actualImage;
    }

    public String getMergedDiffImage() {
        return mergedDiffImage;
    }

    public String getDiffImage() {
        return diffImage;
    }
}
