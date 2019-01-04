
package com.skilrock.ige.api;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import objectRepository.ConfigManager;
import objectRepository.URL;

public class LoginPms {
	public static Response response;
	public static String body;
	private static Logger logger = LoggerFactory.getLogger(LoginPms.class);

	public void versionControl() {
		String postData = "\"deviceName\":\"" + ConfigManager.getProperty("PlatformName") + "\",\"deviceType\":\""
				+ ConfigManager.getProperty("Device") + "\",\"currentVersion\":\""
				+ ConfigManager.getProperty("appVersion") + "\"";
		response = given().header("appVersion", "" + ConfigManager.getProperty("appVersion") + "")
				.contentType("application/json").when().queryParam("requestData", postData).get(URL.versionControl);
		body = response.getBody().asString();

		logger.info("RESPONSE :: " + response + ":: Status code :: " + response.getStatusCode());
		logger.info("Body :: " + body);

	}

	public boolean authenticate(String userName, String password) {
		boolean flag = false;
		try {
			String url = ConfigManager.getProperty("EnvironmentURL_PMS")
					+ "/com/skilrock/pms/mobile/loginMgmt/Action/playerLogin.action?userName=" + userName + "&password="
					+ password + "&deviceName=" + ConfigManager.getProperty("PlatformName") + "&deviceType="
					+ ConfigManager.getProperty("Device") + "&currentVersion=" + ConfigManager.getProperty("appVersion")
					+ "";
			response = given().headers("reqChannel", "" + ConfigManager.getProperty("PlatformName") + "", "appVersion",
					"" + ConfigManager.getProperty("appVersion") + "").contentType("application/json").get(url);
			body = response.getBody().asString();
			logger.info("RESPONSE :: " + response + ":: Status code :: " + response.getStatusCode());
			logger.info("Body :: " + body);
			flag = LoginPmsJson.authenticateUser();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception" + e);
		}
		if (flag) {
			logger.info("login api is working");
			return true;
		} else {
			Assert.fail("Login api not working");
			return false;

		}
	}

}
