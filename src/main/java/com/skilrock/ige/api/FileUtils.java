package com.skilrock.ige.api;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FileUtils {

	public static void writeIntoFile(String data, File file) {
		try {
			if (file == null || data == null)
				return;
			FileWriter fileWriter = new FileWriter(file, true);
			BufferedWriter output = new BufferedWriter(fileWriter);
			output.newLine();
			output.write(data);
			output.close();

		} catch (Exception e) {

		}

	}

	public static String getFormattedData(String sNo, String ticketNo, String winCount, String prizeAmnt) {
		int maxCharSno = 9;
		int maxChartktNo = 12;
		return addWhiteSpace(sNo, maxCharSno - sNo.length()) + "  |  "
				+ addWhiteSpace(ticketNo, maxChartktNo - ticketNo.length()) + "  |  " + addWhiteSpace(winCount, maxCharSno - winCount.length()) + "  |  " + prizeAmnt;
	}

	public static String addWhiteSpace(String data, int whiteSpace) {
		for (int i = 0; i < whiteSpace; i++) {
			data += " ";
		}
		return data;
	}
}
