package com.nakal.utils;


import com.nakal.ScreenExecutor.Configuration;
import static com.nakal.ScreenExecutor.Configuration.baseDirectory;
import static com.nakal.ScreenExecutor.Configuration.screenshotFolder;
import static com.nakal.ScreenExecutor.Configuration.platform;

/**
 * Created by saikrisv on 04/07/16.
 */
public class ScreenPaths {

    String customPath;
    String expectedImage;
    String maskedRegionExpectedImage;
    String maskImage;
    String maskedExpectedImage;
    String actualImage;
    String actualMaskedRegionImage;
    String maskedActualImage;
    String mergedDiffImage;
    String diffImage;


    public ScreenPaths() {
           if (screenshotFolder!=null) {
               customPath = System.getenv("nakal.screenshot.folder")
                       + "/" + platform;
           } else {
               customPath = platform;
           }
    }

    public String getExpectedImage() {
        return expectedImage;
    }

    public void setExpectedImage(String baseLineImageName) {
        this.expectedImage =
                baseDirectory + "/" + customPath
                        + "/baseline_images/" + baseLineImageName + "/" + baseLineImageName
                        + ".png";
    }

    public String getMaskedRegionExpectedImage() {
        return maskedRegionExpectedImage;
    }

    public void setMaskedRegionExpectedImage(String baseLineImageName) {
        this.maskedRegionExpectedImage =
                baseDirectory + "/" + customPath
                        + "/baseline_images/" + baseLineImageName + "/" + "maskedregion_"
                        + baseLineImageName + ".png";
        ;
    }

    public String getMaskImage() {
        return maskImage;
    }

    public void setMaskImage() {

        this.maskImage =
                baseDirectory + "/" + platform + "/mask_images/"
                        + Configuration.maskImage + ".png";
    }

    public String getMaskedExpectedImage() {
        return maskedExpectedImage;
    }

    public void setMaskedExpectedImage(String baseLineImageName) {
        this.maskedExpectedImage =
                baseDirectory + "/" + customPath
                        + "/baseline_images/" + baseLineImageName + "/" + "masked_"
                        + baseLineImageName + ".png";
        ;
    }



    public String getMaskedActualImage() {
        return maskedActualImage;
    }

    public void setMaskedActualImage(String baseLineImageName) {
        this.maskedActualImage =
                baseDirectory + "/target/" + customPath
                        + "/actual_images/" + baseLineImageName + "/" + "masked_"
                        + baseLineImageName + ".png";
        ;
    }



    public String getMergedDiffImage() {
        return mergedDiffImage;
    }

    public void setMergedDiffImage(String baseLineImageName) {
        this.mergedDiffImage =
                baseDirectory + "/target/" + customPath
                        + "/actual_images/" + baseLineImageName + "/" + "difference_"
                        + baseLineImageName + ".png";
        ;
    }



    public String getDiffImage() {
        return diffImage;
    }

    public void setDiffImage(String baseLineImageName) {
        this.diffImage =
                baseDirectory + "/target/" + customPath
                        + "/actual_images/" + baseLineImageName + "/" + "diff_"
                        + baseLineImageName + ".png";
        ;
    }



    public String getActualImage() {
        return actualImage;
    }

    public void setActualImage(String baseLineImageName) {
        this.actualImage =
                baseDirectory + "/target/" + customPath +
                        "/actual_images/" + baseLineImageName + "/" + "actual_"
                        + baseLineImageName + ".png";
        ;
    }

    public String getActualMaskedRegionImage() {
        return actualMaskedRegionImage;
    }

    public void setActualMaskedRegionImage(String baseLineImageName) {
        this.actualMaskedRegionImage =
                baseDirectory + "/target/" + customPath
                        + "/actual_images/" + baseLineImageName + "/" + "actualmaskedregion_"
                        + baseLineImageName + ".png";
        ;
    }


}
