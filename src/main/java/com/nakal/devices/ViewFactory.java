package com.nakal.devices;
import static com.nakal.ScreenExecutor.NakalAttributeValidator.isAndroid;
import static com.nakal.ScreenExecutor.NakalAttributeValidator.isChrome;
import static com.nakal.ScreenExecutor.NakalAttributeValidator.isiOS;
import static com.nakal.ScreenExecutor.NakalAttributeValidator.isSafari;


public class ViewFactory{

	private AndroidDeviceScreen androidFlow;
	private iOSDeviceScreen iosFlow;


	public DeviceInterface getMobilePlatform(String platform) {
		if (platform == null) {
			return null;
		}
		if (isAndroid() || isChrome()) {
			if (androidFlow == null) {
				return androidFlow = new AndroidDeviceScreen();
			}
			return androidFlow;
		}

		if (isiOS() || isSafari()) {
			if (iosFlow == null) {
				return iosFlow = new iOSDeviceScreen();
			}
			return iosFlow;

		}
		return null;

	}

}
