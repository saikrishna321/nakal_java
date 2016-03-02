package com.nakal.devices;

import com.nakal.utils.CommandPrompt;
import com.nakal.utils.Utils;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by saikrisv on 22/02/16.
 */
public class iOSDeviceScreen implements DeviceInterface {
    public ArrayList<String> deviceUDIDiOS = new ArrayList<String>();
    CommandPrompt commandPrompt = new CommandPrompt();
    Utils utils = new Utils();

    public void checkIfIOSDeviceIsConnected() throws IOException {
       if(getIOSUDID().size() == 0){
           System.out.println("No iOS devices Connected....Quiting System");
       }
    }

    public ArrayList<String> getIOSUDID() throws IOException {
        try {
            String getIOSDeviceID = commandPrompt.runCommand("idevice_id --list");
            String[] lines = getIOSDeviceID.split("\n");
            for (int i = 0; i < lines.length; i++) {
                lines[i] = lines[i].replaceAll("\\s+", "");
                deviceUDIDiOS.add(lines[i]);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return deviceUDIDiOS;
    }

    public void captureScreenShot(String screenShotPath,String imagePath){
        utils.createDirectory();
        //imagePath = System.getProperty("user.dir")+"/"+System.getenv("PLATFORM")+"/baseline_images/"+screenShotPath+".png";
        File f = new File(imagePath);
        if(f.exists()){
            System.out.println("BaseLine Image already Exists");
        }else{
            try {
                try {
                    commandPrompt.runCommand("idevicescreenshot " + imagePath);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void captureScreenShot(WebDriver driver, String imagePath) {

    }

    public void captureScreenShot(WebDriver driver, String filePath, String imagePath) {

    }

}
