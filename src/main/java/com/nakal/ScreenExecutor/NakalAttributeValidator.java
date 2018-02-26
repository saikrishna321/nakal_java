package com.nakal.ScreenExecutor;

import com.nakal.utils.YamlReader;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;

import static com.nakal.ScreenExecutor.Configuration.maskImage;
import static com.nakal.ScreenExecutor.Configuration.nakalMode;
import static com.nakal.ScreenExecutor.Configuration.platform;

/**
 * Created by hiteshs on 2/25/18.
 */
public class NakalAttributeValidator {

    public static final String BUILDMODE = "build";
    public static final String COMPAREMODE = "compare";
    public static final String ANDROID="android";
    public static final String CHROME="chrome";
    public static final String IOS="ios";
    public static final String SAFARI="safari";


    public static boolean isBuildMode() {
        return BUILDMODE.equalsIgnoreCase(nakalMode);
    }
    public static boolean isCompareMode(){
        return COMPAREMODE.equalsIgnoreCase(nakalMode);
    }

    public static boolean isAndroid(){
        return ANDROID.equalsIgnoreCase(platform);
    }
    public static boolean isChrome(){
        return CHROME.equalsIgnoreCase(platform);
    }

    public static boolean isiOS(){
        return IOS.equalsIgnoreCase(platform);
    }
    public static boolean isSafari(){
        return SAFARI.equalsIgnoreCase(platform);
    }
    public static boolean isMaskImagePresent() {
        return maskImage!=null && !maskImage.isEmpty();
    }

    public static boolean isFullScreen() throws FileNotFoundException {
        final String fullScreenAttr="fullScreen";
        boolean result= false;
        String fullScreen=null;
        if(isYamlPresent() && hasAttributeInYaml(fullScreenAttr)) {
                fullScreen = YamlReader.getInstance().getValue(fullScreenAttr).toString();
        }
        return fullScreen!=null && fullScreen.equalsIgnoreCase("true");
    }

    public static boolean isIgnoreRegion(String baseLineImageName) throws FileNotFoundException {
        Object mask=null;
        if(isMaskImagePresent() && isYamlPresent() && hasAttributeInYaml(maskImage)) {
            mask = YamlReader.getInstance().getValue(maskImage);
        }
        return mask!=null && ((LinkedHashMap)mask).get(baseLineImageName)!=null;
    }
    public static boolean isYamlPresent(){
        return YamlReader.getInstance().checkIfYamlFileExists();
    }
    public static boolean hasAttributeInYaml(String value){
        return YamlReader.getInstance().getAllResultsMap().containsKey(value);
    }
}
