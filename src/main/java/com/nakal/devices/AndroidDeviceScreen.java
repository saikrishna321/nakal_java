package com.nakal.devices;

import com.github.cosysoft.device.android.AndroidDevice;
import com.github.cosysoft.device.android.impl.AndroidDeviceStore;
import com.github.cosysoft.device.image.ImageUtils;
import com.nakal.imageutil.ImageUtil;
import com.nakal.utils.Utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.TreeSet;

/**
 * Created by saikrisv on 22/02/16.
 */
public class AndroidDeviceScreen implements DeviceInterface {

    TreeSet<AndroidDevice> devices;
    AndroidDevice device;
    Utils util = new Utils();
    ImageUtil imageUtil = new ImageUtil();
    int deviceSize;

    public AndroidDeviceScreen() {
        getDeviceConnected();
        if (checkIfDevicesAreConnected() == false) {
            System.out.println("No Devices Connected.... Quiting the execution");
            System.exit(0);
        }
    }

    public AndroidDevice getDeviceConnected() {
        if (System.getenv("UDID") == null || System.getenv("UDID").isEmpty()) {
            devices = AndroidDeviceStore.getInstance()
                .getDevices();
            deviceSize = devices.size();
            device = devices.pollFirst();
            return device;
        } else {
            device = AndroidDeviceStore.getInstance().getDeviceBySerial(System.getenv("UDID"));
            if(!device.getName().isEmpty()){
                deviceSize = 1;
            }
            return device;
        }
    }

    public boolean checkIfDevicesAreConnected() {
        if (deviceSize > 0) {
            return true;
        }
        return false;
    }

    public String getDeviceName() {
        return device.getName().toString();
    }


    public void captureScreenShot( String imagePath) {
        //util.createDirectory(arg);
        String directoryPath=null;
        File file = new File(imagePath);
        if (file.exists()) {
            System.out.println("BaseLine Image already Exists");
        } else {
            if(!file.isDirectory())
                directoryPath=util.getParentDirectoryFromFile(file);
                util.createDirectory(directoryPath);
            BufferedImage image = device.takeScreenshot();
            ImageUtils.writeToFile(image, imagePath);
        }
    }

}
