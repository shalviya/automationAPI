package com.skilrock.ige.api;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IgeJson {
	private static Logger logger = LoggerFactory.getLogger(IgeJson.class);

	public static final String WIN_AMOUNT = "amount";
	public static final String TICKET_NO = "ticketNo"; 

	public static Map<String, Object> gameSale() {
		try {
			if (Ige.body == null)
				return null;
			JSONObject obj = new JSONObject(Ige.body);
			int errorCode = obj.optInt("errorCode", -1);
			if (errorCode == 101)
				Assert.fail("Insufficient Balance");
			if (errorCode == 113)
				Assert.fail("Invalid sesssion ID");
			if (errorCode != 0)
				return null;

			// hashmap
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(TICKET_NO, obj.optLong("tktNum"));
			map.put(WIN_AMOUNT, obj.optDouble("prizeAmount"));
			return map;
		} catch (JSONException e) {
			e.printStackTrace();
			logger.error("Exception" + e);
			return null;
		}
	}

}
