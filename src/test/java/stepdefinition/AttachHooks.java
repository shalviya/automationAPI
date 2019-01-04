package stepdefinition;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import objectRepository.ConfigManager;

public class AttachHooks {

	private Scenario scenario;
	private static Logger LOGGER = LoggerFactory.getLogger(AttachHooks.class);

	@Before
	/*public static void createServerStatusFile() throws IOException {
		File file = new File("testdata.txt");

		// Create the file
		if (file.createNewFile()) {
			LOGGER.info("File is created!");
		} else {
			LOGGER.info("File already exists.");
		}
	}*/

	public void setUp(Scenario scenario) throws IOException {
		LOGGER.info("Inside set up method of before hook");
		ConfigManager.loadConfig();
		this.scenario = scenario;
		LOGGER.info(scenario.getName());
		if (ConfigManager.getProperty("ExecutionPlatform").equalsIgnoreCase("Api")) {
			LOGGER.info("Api execution started");
		}
	}

	@After
	public void tearDown() throws InstantiationException, IllegalAccessException, IOException {

	}

}