package stepdefinition;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skilrock.ige.api.FileUtils;
import com.skilrock.ige.api.Ige;
import com.skilrock.ige.api.LoginPms;

import cucumber.api.java.en.Given;
import objectRepository.ConfigManager;

public class User_api_function {
	LoginPms loginPms = new LoginPms();

	Ige ige = new Ige();;
	boolean flag;
	private static Logger logger = LoggerFactory.getLogger(User_api_function.class);

	@Given("^player Authenticated via (.*)$")
	public void app_user_is_authenticated(String merchant) throws Throwable {

		if (merchant.contains("PMS")) {
			// loginPms.versionControl();
			flag = loginPms.authenticate(ConfigManager.getProperty("Username"), ConfigManager.getProperty("Password"));

		}
		if (flag) {
			logger.info("User is authenticated");
		} else {
			logger.error("User is not authenticated");
			Assert.fail();
		}
		

	}

	@Given("^User performs sale of (\\d+) and finishes the game$")
	public void sale(String gameNumber) throws Throwable {
		String dirPath = ConfigManager.getProperty("dirPath");
		if (dirPath == null || dirPath.isEmpty()) {

			return;
		}
		File file = new File(dirPath);
		if (!file.exists())
			file.mkdirs();

		File gameInfo = new File(dirPath, gameNumber + "_saleData.txt");
		if (gameInfo.exists())
			gameInfo.delete();
		FileUtils.writeIntoFile(FileUtils.getFormattedData("SNO", "TICKET NUM", "WIN COUNT", "AMOUNT"), gameInfo);

		for (int i = 0; i < 5000; i++) {
			ige.gameSale(i + 1, gameNumber);// json object = {key tkt no , amt)
			ige.finishGame(gameNumber);
			// ige.fileWriting();
		}
	}

	/*
	 * @Then("^data is collected into a file$") public void
	 * data_is_collected_into_a_file() throws Throwable {
	 * 
	 * }
	 */

}
