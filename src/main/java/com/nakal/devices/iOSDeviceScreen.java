package com.nakal.devices;

import com.nakal.ScreenExecutor.NakalAttributeValidator;
import com.nakal.utils.CommandPrompt;
import com.nakal.utils.Utils;
import com.thoughtworks.device.Device;
import com.thoughtworks.device.SimulatorManager;
import com.thoughtworks.iOS.IOSManager;
import org.apache.commons.io.FilenameUtils;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saikrisv on 22/02/16.
 */
public class iOSDeviceScreen implements DeviceInterface {
    public ArrayList<String> deviceUDIDiOS = new ArrayList<String>();
    CommandPrompt commandPrompt = new CommandPrompt();
    SimulatorManager simulatorManager = new SimulatorManager();
    Utils utils = new Utils();

    public ArrayList<String> getIOSUDID() throws IOException {
        List<Device> allAvailableDevices = new IOSManager().getDevices();
        allAvailableDevices.forEach(iosudid -> {
            deviceUDIDiOS.add(iosudid.getUdid());
        });
        return deviceUDIDiOS;
    }

    public void captureScreenShot(String screenShotPath,String imagePath){
        String directoryPath=null;
        File file = new File(imagePath);
        String fileName= file.getName();
        if(NakalAttributeValidator.isBuildMode() && file.exists()){
            System.out.println("BaseLine Image already Exists");
        }else{
            if(!file.isDirectory())
                directoryPath=utils.getParentDirectoryFromFile(file);
            utils.createDirectory(directoryPath);
            try {
                if(getIOSUDID().size() != 0) {
                    commandPrompt.runCommand("idevicescreenshot " + imagePath);
                } else if(simulatorManager.getAllBootedSimulators("iOS").size() != 0) {
                    fileName= FilenameUtils.removeExtension(fileName);
                    String UDID = simulatorManager.getAllBootedSimulators("iOS").get(0).getUdid();
                    simulatorManager.captureScreenshot(UDID, fileName, directoryPath, "png");
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
