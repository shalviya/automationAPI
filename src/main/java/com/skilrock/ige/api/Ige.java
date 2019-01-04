package com.skilrock.ige.api;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.restassured.response.Response;

import objectRepository.ConfigManager;

public class Ige {

	Response resp;
	public static String body;
	private static Logger logger = LoggerFactory.getLogger(Ige.class);

	private Map<String, Object> saleData;

	private int winCount;

	public boolean gameSale(int sNo, String gameNumber) {
		java.net.URL obj;
		try {
			obj = new java.net.URL(
					"http://102.177.192.193:8383/InstantGameEngine/gamePlay/api/loadPortalGame.action?gameNumber="
							+ gameNumber
							+ "&gameMode=Buy&domainName=pms&merchantKey=3&secureKey=12345678&currencyCode=USD&lang=eng&isImageGeneration=true&clientType=image_generation&deviceType=Mobile&appType=android&playerId=1001502&merchantSessionId="
							+ LoginPmsJson.sessionId);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");

				
			body = getRespose(con.getInputStream());
			logger.info(body);
			saleData = IgeJson.gameSale();
			if (saleData != null) {
				if ((double) saleData.get(IgeJson.WIN_AMOUNT) > 0)
					winCount++;
				File gameInfo = new File(ConfigManager.getProperty("dirPath"), gameNumber + "_saleData.txt");
				FileUtils.writeIntoFile(FileUtils.getFormattedData("" + sNo, "" + saleData.get(IgeJson.TICKET_NO),
						"" + winCount, "" + saleData.get(IgeJson.WIN_AMOUNT)), gameInfo);
			}

			return saleData != null;

		} catch (IOException e) {
			logger.error("game sale not working properly");
			return false;
			}
	}

	public String getRespose(InputStream in) {
		StringBuilder s = new StringBuilder();
		int a = -1;
		byte b[] = new byte[2048];
		try {
			while ((a = in.read(b)) != -1) {
				s.append(new String(b, 0, a));
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		return s.toString();
	}

	public void finishGame(String gameNumber) {

		// http://102.177.192.193:8383/InstantGameEngine/gamePlay/api/gameFinishCall.action?
		// gameNumber=202&gameMode=Buy&domainName=pms&merchantKey=3&secureKey=12345678&screenSize=1080x1920
		// &currencyCode=USD&lang=eng&isImageGeneration=true&clientType=image_generation
		// &merchantSessionId=b4spSLSsJMG1aostgK6Gv5Tk.fqwFGoswSsv5&deviceType=Mobile&appType=android
		// &playerId=1001501&ticketNbr=323010075012
		try {
			URL obj = new java.net.URL(
					"http://102.177.192.193:8383/InstantGameEngine/gamePlay/api/gameFinishCall.action?" + "gameNumber="
							+ gameNumber + "&gameMode=Buy&domainName=pms&merchantKey=3&secureKey=12345678"
							+ "&currencyCode=USD&lang=eng&isImageGeneration=true&clientType=image_generation"
							+ "&deviceType=Mobile&appType=android&playerId=1001502&merchantSessionId="
							+ LoginPmsJson.sessionId + "&ticketNbr=" + saleData.get(IgeJson.TICKET_NO));
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");

			body = getRespose(con.getInputStream());
			logger.info(body);
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}

	/*
	 * public void fileWriting(Object object) throws IOException {
	 * 
	 * 
	 * try { bw.write(object.toString()); } catch (Exception e) {
	 * logger.error("Exception : " + e.getMessage()); }
	 * 
	 * 
	 * 
	 */

}
