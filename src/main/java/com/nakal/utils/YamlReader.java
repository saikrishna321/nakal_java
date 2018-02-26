package com.nakal.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static com.nakal.ScreenExecutor.Configuration.baseDirectory;
import static com.nakal.ScreenExecutor.Configuration.maskImage;

/**
 * Created by shridhk on 12/20/17.
 */
public class YamlReader {
    static Map<String, Object> result;

    private static YamlReader yamlReader;

    private YamlReader() {
        final String fileName = getPath();
        Yaml yaml = new Yaml();
        InputStream yamlParams = null;
        try {
            yamlParams = new FileInputStream(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        result = (Map<String, Object>) yaml.load(yamlParams);
    }

    public static YamlReader getInstance() {
        if (yamlReader == null) {
            yamlReader = new YamlReader();
        }
        return yamlReader;
    }

    private static String getPath() {
        return baseDirectory + "/nakal.yaml";
    }

    public static Object getValue(String key) {
        return result.get(key);
    }

    public static Map<String, Object> getAllResultsMap() {
        return result;
    }

    public static boolean checkIfYamlFileExists() {
        final String fileName = getPath();
        File file = new File(fileName);
        return file.exists();
    }

    public String fetchValueFromYaml(String screenName) throws FileNotFoundException {
        Set mask_region =
                ((LinkedHashMap) ((LinkedHashMap) YamlReader.getInstance().getValue(maskImage))
                        .get(screenName)).entrySet();
        String maskingRegions = "";
        for (Object regions : mask_region) {
            maskingRegions =
                    maskingRegions + " rectangle " + regions.toString().split("=")[1].toString()
                            .replace("[", "").replace("]", "").trim();
        }
        return maskingRegions;
    }


}
