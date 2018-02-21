package com.nakal.devices;
import static com.nakal.ScreenExecutor.NakalExecutor.isAndroid;
import static com.nakal.ScreenExecutor.NakalExecutor.isChrome;
import static com.nakal.ScreenExecutor.NakalExecutor.isiOS;
import static com.nakal.ScreenExecutor.NakalExecutor.isSafari;


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
