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
    private String maskedRegionExpectedImage;
    private String maskImage;
    private String maskedExpectedImage;
    private String actualImage;
    private String maskedRegionActualImage;
    private String maskedActualImage;
    private String mergedDiffImage;
    private String diffImage;


    public ScreenPaths(String baseLineImageName) {
           if (screenshotFolder!=null) {
               customPath = System.getenv("nakal.screenshot.folder")
                       + File.separator + platform;
           } else {
               customPath = platform;
           }
           init(baseLineImageName);
    }


    private void init(String baseLineImageName){

        setActualImage(baseLineImageName);
        setMaskedRegionActualImage(baseLineImageName);
        setExpectedImage(baseLineImageName);
        setMaskedActualImage(baseLineImageName);
        setMaskedExpectedImage(baseLineImageName);
        setDiffImage(baseLineImageName);
        setMaskedRegionExpectedImage(baseLineImageName);
        setMaskImage();
        setMergedDiffImage(baseLineImageName);
    }

    private void setExpectedImage(String baseLineImageName) {
        this.expectedImage =
                getBasePathForBaseLine(baseLineImageName) + baseLineImageName
                        + ".png";
    }

    private void setMaskedRegionExpectedImage(String baseLineImageName) {
        this.maskedRegionExpectedImage =
                getBasePathForBaseLine(baseLineImageName)+ "maskedregion_"
                        + baseLineImageName + ".png";
    }

    private void setMaskImage() {
        this.maskImage =
                baseDirectory + File.separator + platform + "/mask_images/"
                        + Configuration.maskImage + ".png";
    }

    private void setMaskedExpectedImage(String baseLineImageName) {
        this.maskedExpectedImage =
                getBasePathForBaseLine(baseLineImageName)+ "masked_"
                        + baseLineImageName + ".png";
    }

    private void setMaskedActualImage(String baseLineImageName) {
        this.maskedActualImage =
                getBasePathForTargetActualImage(baseLineImageName)+ "masked_"
                        + baseLineImageName + ".png";
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
                getBasePathForTargetActualImage(baseLineImageName)+ "actual_"
                        + baseLineImageName + ".png";
    }

    private void setMaskedRegionActualImage(String baseLineImageName) {
        this.maskedRegionActualImage =
                getBasePathForTargetActualImage(baseLineImageName) + "actualmaskedregion_"
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

    public String getMaskedRegionExpectedImage() {
        return maskedRegionExpectedImage;
    }

    public String getMaskImage() {
        return maskImage;
    }

    public String getMaskedExpectedImage() {
        return maskedExpectedImage;
    }

    public String getActualImage() {
        return actualImage;
    }

    public String getMaskedRegionActualImage() {
        return maskedRegionActualImage;
    }

    public String getMaskedActualImage() {
        return maskedActualImage;
    }

    public String getMergedDiffImage() {
        return mergedDiffImage;
    }

    public String getDiffImage() {
        return diffImage;
    }
}
