package com.nakal.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by saikrisv on 22/02/16.
 */
public class Utils {

    public File file;

    public void createDirectory(String fileName) {
        if (System.getenv("NAKAL_MODE").equalsIgnoreCase("build")) {
            deleteDirectory(
                System.getProperty("user.dir") + "/" + System.getenv("PLATFORM")
                        + "/baseline_images/" + fileName);
            file = new File(
                System.getProperty("user.dir") + "/" + System.getenv("PLATFORM")
                        + "/baseline_images/" + fileName);
            if (!file.exists()) {
                if (file.mkdirs()) {
                    System.out.println("BaseLine Image Directory is created!");
                } else {
                    System.out.println("Failed to create BaseLine image directory!");
                }
            }

        }

        if (System.getenv("NAKAL_MODE").equalsIgnoreCase("compare")) {
            file = new File(
                System.getProperty("user.dir") + "/target/" + System.getenv("PLATFORM")
                        + "/actual_images/" + fileName);
            if (!file.exists()) {
                if (file.mkdirs()) {
                    System.out.println("Actual Image Directory is created!");
                } else {
                    System.out.println("Failed to create BaseLine image directory!");
                }
            }
        }
    }

    public void deleteDirectory(String path) {

        File file = new File(path);
        if (file.isDirectory()) {
            System.out.println("Deleting Directory :" + path);
            try {
                FileUtils.deleteDirectory(new File(path)); //deletes the whole folder
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out.println("Deleting File :" + path);
            //it is a simple file. Proceed for deletion
            file.delete();
        }

    }
}
