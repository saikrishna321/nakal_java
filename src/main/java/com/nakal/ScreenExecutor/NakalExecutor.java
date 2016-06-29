package com.nakal.ScreenExecutor;

import com.nakal.capturescreen.ScreenShooter;
import com.nakal.devices.WebScreen;
import com.nakal.imageutil.ImageUtil;
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
    public File file;

    /**
     * @param baseLineImageName
     * @return false if actual and expected images are not similar and generate a difference Image
     */
    public boolean nakalExecutorNativeCompare(String baseLineImageName) {
        String expectedImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM")+"/"+System.getenv("APP") + "/baseline_images/" + baseLineImageName + ".png";
        String maskImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") + "/mask_images/" + System.getenv("MASKIMAGE") + ".png";
        String maskedExpectedImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") +"/"+System.getenv("APP")+ "/baseline_images/"+"masked_"+baseLineImageName + ".png";
        if (System.getenv("NAKAL_MODE").equalsIgnoreCase("build")) {
            screenCapture(baseLineImageName, expectedImage);
            try {
                //Cropped the notification bar and create a maskImage to compare
                imageUtil.maskImage(expectedImage,maskImage,maskedExpectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IM4JavaException e) {
                e.printStackTrace();
            }
            return true;
        } else if (System.getenv("NAKAL_MODE").equalsIgnoreCase("compare")) {
            try {
                String actualImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") + "/"+System.getenv("APP")+"/actual_images/actual_" + baseLineImageName + ".png";
                String diffImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") +"/"+System.getenv("APP")+ "/actual_images/diff_" + baseLineImageName + ".png";
                String mergedDiffImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") +"/"+System.getenv("APP")+ "/actual_images/difference_" + baseLineImageName + ".png";
                String maskedActualImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") +"/"+System.getenv("APP")+ "/actual_images/"+"masked_"+baseLineImageName + ".png";
                screenCapture("actual_" + baseLineImageName, actualImage);
                try {
                    //Cropped the notification bar and create a maskImage to compare
                    imageUtil.maskImage(actualImage,maskImage,maskedActualImage);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IM4JavaException e) {
                    e.printStackTrace();
                }

                if(imageUtil.compareImages(maskedExpectedImage, maskedActualImage, diffImage) == true){
                    return true;
                }else{
                    imageUtil.mergeImagesHorizontally(expectedImage,actualImage,diffImage,mergedDiffImage);
                    file= new File(diffImage);
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


    public boolean nakalExecutorWebCompare(WebDriver driver, String baseLineImageName) {
        String expectedImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM")+"/"+System.getenv("APP") + "/baseline_images/" + baseLineImageName + ".png";
        if (System.getenv("NAKAL_MODE").equalsIgnoreCase("build")) {
            webScreen.captureScreenShot(driver,expectedImage);
            return true;
        } else if (System.getenv("NAKAL_MODE").equalsIgnoreCase("compare")) {
            try {
                String actualImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") + "/"+System.getenv("APP")+"/actual_images/actual_" + baseLineImageName + ".png";
                String diffImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") +"/"+System.getenv("APP")+ "/actual_images/diff_" + baseLineImageName + ".png";
                String mergedDiffImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") +"/"+System.getenv("APP")+ "/actual_images/difference_" + baseLineImageName + ".png";
                webScreen.captureScreenShot(driver,actualImage);
                if(imageUtil.compareImages(expectedImage, actualImage, diffImage) == true){
                    return true;
                }else{
                    imageUtil.mergeImagesHorizontally(expectedImage,actualImage,diffImage,mergedDiffImage);
                    file= new File(diffImage);
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


    public boolean nakalExecutorWebCompare(WebDriver driver, String baseLineImageName,int threshold) {
        String expectedImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM")+"/"+System.getenv("APP") + "/baseline_images/" + baseLineImageName + ".png";
        if (System.getenv("NAKAL_MODE").equalsIgnoreCase("build")) {
            webScreen.captureScreenShot(driver,expectedImage);
            return true;
        } else if (System.getenv("NAKAL_MODE").equalsIgnoreCase("compare")) {
            try {
                String actualImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") + "/"+System.getenv("APP")+"/actual_images/actual_" + baseLineImageName + ".png";
                String diffImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") +"/"+System.getenv("APP")+ "/actual_images/diff_" + baseLineImageName + ".png";
                String mergedDiffImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") +"/"+System.getenv("APP")+ "/actual_images/difference_" + baseLineImageName + ".png";
                webScreen.captureScreenShot(driver,actualImage);
                if(imageUtil.compareImages(expectedImage, actualImage, diffImage , threshold) == true){
                    file= new File(diffImage);
                    file.delete();
                    return true;
                }else{
                    imageUtil.mergeImagesHorizontally(expectedImage,actualImage,diffImage,mergedDiffImage);
                    file= new File(diffImage);
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


    public boolean nakalExecutorNativeCompare(String baseLineImageName,int threshold) {
        String expectedImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM")+"/"+System.getenv("APP") + "/baseline_images/" + baseLineImageName + ".png";
        String maskImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") + "/mask_images/" + System.getenv("MASKIMAGE") + ".png";
        String maskedExpectedImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") +"/"+System.getenv("APP")+ "/baseline_images/"+"masked_"+baseLineImageName + ".png";
        if (System.getenv("NAKAL_MODE").equalsIgnoreCase("build")) {
            screenCapture(baseLineImageName, expectedImage);
            try {
                //Cropped the notification bar and create a maskImage to compare
                imageUtil.maskImage(expectedImage,maskImage,maskedExpectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IM4JavaException e) {
                e.printStackTrace();
            }
            return true;
        } else if (System.getenv("NAKAL_MODE").equalsIgnoreCase("compare")) {
            try {
                String actualImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") + "/"+System.getenv("APP")+"/actual_images/actual_" + baseLineImageName + ".png";
                String diffImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") +"/"+System.getenv("APP")+ "/actual_images/diff_" + baseLineImageName + ".png";
                String mergedDiffImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") +"/"+System.getenv("APP")+ "/actual_images/difference_" + baseLineImageName + ".png";
                String maskedActualImage = System.getProperty("user.dir") + "/" + System.getenv("PLATFORM") +"/"+System.getenv("APP")+ "/actual_images/"+"masked_"+baseLineImageName + ".png";
                screenCapture("actual_" + baseLineImageName, actualImage);
                try {
                    //Cropped the notification bar and create a maskImage to compare
                    imageUtil.maskImage(actualImage,maskImage,maskedActualImage);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IM4JavaException e) {
                    e.printStackTrace();
                }

                if(imageUtil.compareImages(maskedExpectedImage, maskedActualImage, diffImage, threshold) == true){
                    file= new File(diffImage);
                    file.delete();
                    return true;
                }else{
                    imageUtil.mergeImagesHorizontally(maskedExpectedImage,maskedActualImage,diffImage,mergedDiffImage);
                    file= new File(diffImage);
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

}
