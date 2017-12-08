package com.nakal.utils;

/**
 * Created by saikrisv on 04/07/16.
 */
public class ScreenPaths {

    String customPath;

    public ScreenPaths() {
           if (System.getenv("FOLDER_NAME")!=null) {
               customPath = System.getenv("FOLDER_NAME")
                       + "/" + System.getenv("Platform");
           } else {
               customPath = System.getenv("Platform");
           }
    }

    public String getExpectedImage() {
        return expectedImage;
    }

    public void setExpectedImage(String baseLineImageName) {
        this.expectedImage =
                System.getProperty("user.dir") + "/" + customPath
                        + "/baseline_images/" + baseLineImageName + "/" + baseLineImageName
                        + ".png";
    }

    public String getMaskedRegionExpectedImage() {
        return maskedRegionExpectedImage;
    }

    public void setMaskedRegionExpectedImage(String baseLineImageName) {
        this.maskedRegionExpectedImage =
                System.getProperty("user.dir") + "/" + customPath
                        + "/baseline_images/" + baseLineImageName + "/" + "maskedregion_"
                        + baseLineImageName + ".png";
        ;
    }

    public String getMaskImage() {
        return maskImage;
    }

    public void setMaskImage() {
        String platform = System.getenv("Platform");
        if (platform.equalsIgnoreCase("chrome")
                || platform.equalsIgnoreCase("android") ) {
            platform = "android";
        } else {
            platform = "ios";
        }
        this.maskImage =
                System.getProperty("user.dir") + "/" + platform + "/mask_images/"
                        + System.getenv("MASKIMAGE") + ".png";
    }

    public String getMaskedExpectedImage() {
        return maskedExpectedImage;
    }

    public void setMaskedExpectedImage(String baseLineImageName) {
        this.maskedExpectedImage =
                System.getProperty("user.dir") + "/" + customPath
                        + "/baseline_images/" + baseLineImageName + "/" + "masked_"
                        + baseLineImageName + ".png";
        ;
    }

    String expectedImage;
    String maskedRegionExpectedImage;
    String maskImage;
    String maskedExpectedImage;
    String actualImage;
    String actualMaskedRegionImage;

    public String getMaskedActualImage() {
        return maskedActualImage;
    }

    public void setMaskedActualImage(String baseLineImageName) {
        this.maskedActualImage =
                System.getProperty("user.dir") + "/target/" + System.getenv("Platform")
                        + "/actual_images/" + baseLineImageName + "/" + "masked_"
                        + baseLineImageName + ".png";
        ;
    }

    String maskedActualImage;

    public String getMergedDiffImage() {
        return mergedDiffImage;
    }

    public void setMergedDiffImage(String baseLineImageName) {
        this.mergedDiffImage =
                System.getProperty("user.dir") + "/target/" + System.getenv("Platform")
                        + "/actual_images/" + baseLineImageName + "/" + "difference_"
                        + baseLineImageName + ".png";
        ;
    }

    String mergedDiffImage;

    public String getDiffImage() {
        return diffImage;
    }

    public void setDiffImage(String baseLineImageName) {
        this.diffImage =
                System.getProperty("user.dir") + "/target/" + System.getenv("Platform")
                        + "/actual_images/" + baseLineImageName + "/" + "diff_"
                        + baseLineImageName + ".png";
        ;
    }

    String diffImage;

    public String getActualImage() {
        return actualImage;
    }

    public void setActualImage(String baseLineImageName) {
        this.actualImage =
                System.getProperty("user.dir") + "/target/" + System.getenv("Platform") +
                        "/actual_images/" + baseLineImageName + "/" + "actual_"
                        + baseLineImageName + ".png";
        ;
    }

    public String getActualMaskedRegionImage() {
        return actualMaskedRegionImage;
    }

    public void setActualMaskedRegionImage(String baseLineImageName) {
        this.actualMaskedRegionImage =
                System.getProperty("user.dir") + "/target/" + System.getenv("Platform")
                        + "/actual_images/" + baseLineImageName + "/" + "actualmaskedregion_"
                        + baseLineImageName + ".png";
        ;
    }


}
