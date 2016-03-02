package com.nakal.devices;

import com.nakal.utils.Utils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by saikrisv on 29/02/16.
 */
public class WebScreen {
        Utils utils = new Utils();

        public void captureScreenShot(WebDriver driver,String imagePath) {
            utils.createDirectory();
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(scrFile, new File(imagePath));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
