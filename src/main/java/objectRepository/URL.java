package objectRepository;

public class URL {
	public static String versionControl = ConfigManager.getProperty("EnvironmentURL_PMS")
			+ "/com/skilrock/pms/mobile/playerInfo/Action/versionControl.action?deviceName="
			+ ConfigManager.getProperty("PlatformName") + "&deviceType=" + ConfigManager.getProperty("Device")
			+ "&currentVersion=" + ConfigManager.getProperty("appVersion") + "";


			
}
