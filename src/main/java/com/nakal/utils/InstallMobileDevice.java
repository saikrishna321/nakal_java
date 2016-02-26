package com.nakal.utils;

import java.io.IOException;

/**
 * Created by saikrisv on 22/02/16.
 */
public class InstallMobileDevice {

    CommandPrompt cmd = new CommandPrompt();
    public void checkIfMobileDeviceApiIsInstalled() throws InterruptedException, IOException {
        boolean checkMobileDevice = cmd.runCommand("brew list").contains("ideviceinstaller");
        if (checkMobileDevice) {
            System.out.println("iDeviceInstaller already exists");
        } else {
            System.out.println("Brewing mobileDevice API....");
            cmd.runCommand("brew install ideviceinstaller");
        }

    }
}
