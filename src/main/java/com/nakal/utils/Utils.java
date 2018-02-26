package com.nakal.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by saikrisv on 22/02/16.
 */
public class Utils {

    public void createDirectory(String directoryPath) {

        deleteDirectory(directoryPath);
        File file = new File(directoryPath);
        if (!file.exists()) {
            if (file.mkdirs()) {
                System.out.println("BaseLine Image Directory is created!");
            } else {
                System.out.println("Failed to create BaseLine image directory!");
            }
        }
    }

    private void deleteDirectory(String path) {

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

    public String getParentDirectoryFromFile(File file) {
        return file.getParent();
    }
}
