package com.nakal.devices;

import com.nakal.utils.CommandPrompt;
import com.nakal.utils.Utils;
import com.thoughtworks.device.SimulatorManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by saikrisv on 22/02/16.
 */
public class iOSDeviceScreen implements DeviceInterface {
    public ArrayList<String> deviceUDIDiOS = new ArrayList<String>();
    CommandPrompt commandPrompt = new CommandPrompt();
    SimulatorManager simulatorManager = new SimulatorManager();
    Utils utils = new Utils();

    public void checkIfIOSDeviceIsConnected() throws IOException {
       if(getIOSUDID().size() == 0){
           System.out.println("No iOS devices Connected....Quiting System");
       }
    }

    public ArrayList<String> getIOSUDID() throws IOException {
        try {
            String getIOSDeviceID = commandPrompt.runCommand("idevice_id --list");
            if(getIOSDeviceID.trim().equalsIgnoreCase("")) {
                return deviceUDIDiOS;
            }
            String[] lines = getIOSDeviceID.trim().split("\n");
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
        utils.createDirectory(screenShotPath);
        File f = new File(imagePath);
        if(f.exists()){
            System.out.println("BaseLine Image already Exists");
        }else{
            try {
                if(getIOSUDID().size() != 0) {
                    commandPrompt.runCommand("idevicescreenshot " + imagePath);
                } else if(simulatorManager.getAllBootedSimulators("iOS").size() != 0) {
                    if (System.getenv("NAKAL_MODE").equalsIgnoreCase("compare")) {
                        screenShotPath = "actual_" + screenShotPath;
                    }
                    imagePath = imagePath.replace(screenShotPath + ".png", "");
                    String UDID = simulatorManager.getAllBootedSimulators("iOS").get(0).getUdid();
                    simulatorManager.captureScreenshot(UDID, screenShotPath, imagePath, "png");
                } else {
                    System.exit(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
