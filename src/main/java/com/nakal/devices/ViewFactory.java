package com.nakal.devices;


public class ViewFactory{

	private AndroidDeviceScreen androidFlow;
	private iOSDeviceScreen iosFlow;


	public DeviceInterface getMobilePlatform(String platform) {
		if (platform == null) {
			return null;
		}
		if (platform.equalsIgnoreCase("android") || platform.equalsIgnoreCase("chrome")) {
			if (androidFlow == null) {
				return androidFlow = new AndroidDeviceScreen();
			}
			return androidFlow;
		}

		if (platform.equalsIgnoreCase("ios") || platform.equalsIgnoreCase("safari")) {
			if (iosFlow == null) {
				return iosFlow = new iOSDeviceScreen();
			}
			return iosFlow;

		}
		return null;

	}

}
