package com.skilrock.ige.api;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPmsJson {
	public static String sessionId;
	private static Logger logger = LoggerFactory.getLogger(LoginPmsJson.class);

	public static boolean authenticateUser() {
		if (LoginPms.body == null)
			return false;
		try {
			JSONObject obj = new JSONObject(LoginPms.body);
			if (obj.optInt("errorCode", 0) != 0)
				return false;

			sessionId = obj.optString("sessionId", null).replaceAll("\\+", "%2B");
			return sessionId != null;
		} catch (JSONException e) {
			e.printStackTrace();
			
			
			logger.error("Exception" + e);
		}
		return false;
	}
}
