package com.nakal.devices;


public class ViewFactory{

	private AndroidDeviceScreen androidFlow;
	private iOSDeviceScreen iosFlow;


	public DeviceInterface getMobilePlatform(String platform) {
		if (platform == null) {
			return null;
		}
		if (platform.equalsIgnoreCase("android")) {
			if (androidFlow == null) {
				return androidFlow = new AndroidDeviceScreen();
			}
			return androidFlow;
		}

		if (platform.equalsIgnoreCase("ios")) {
			if (iosFlow == null) {
				return iosFlow = new iOSDeviceScreen();
			}
			return iosFlow;

		}
		return null;

	}

}
