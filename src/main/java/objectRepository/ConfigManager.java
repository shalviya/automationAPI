package objectRepository;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class ConfigManager {
	static Properties properties;

	public static void loadConfig() {
		File file = new File("Config.properties");

		Properties properties = new Properties();
		try {
			FileInputStream fileInput = new FileInputStream(file);
			properties.load(fileInput);
			ConfigManager.properties = properties;
			fileInput.close();
		} catch (Exception e) {
			System.out.println("Exception occurred while loading config.properties " + e.getMessage());
		}
	}

	
	public static String getProperty(String key) {
		try {
			loadConfig();
			return properties.getProperty(key);
		} catch (Exception e) {
			return null;
		}

	}

	public static String setProperty(String key, String value) {
		try {
			String previousValue = (String) properties.setProperty(key, value);
			System.out.println(previousValue);
			return previousValue;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
