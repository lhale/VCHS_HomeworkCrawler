package Utilties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * @author Crunchify.com
 * Ref: http://crunchify.com/java-properties-file-how-to-read-config-properties-values-in-java/
 * 
 */
 
public class CrunchifyGetPropertyValues {
	String result = "";
	InputStream inputStream;
 
	public ConfigProperties getPropValues() throws IOException {
		ConfigProperties config_properties = null;
		
		try {
			Properties props = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream("../../resources/" + propFileName);
	        if ( inputStream == null) {	// All getResourceAsStream iterations always fail, so just use regular file stream (wth)
	        	 inputStream = new FileInputStream("resources\\" + propFileName);
	        }
	        if ( inputStream == null) {
	        	 inputStream = new FileInputStream(System.getProperty("user.dir") + "\\resources\\" + propFileName);
	        }
	        if ( inputStream == null) {
	        	 inputStream = new FileInputStream(System.getProperty("user.dir") + "/resources/" + propFileName);
	        }
			if ( inputStream == null)
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the local path (current dir=" + System.getProperty("user.dir") + ")");
			
			props.load(inputStream);
 
			Date datetime = new Date(System.currentTimeMillis());
			config_properties = new ConfigProperties(props.getProperty("login_destination"),
					props.getProperty("username"), props.getProperty("password"), 
					props.getProperty("fill_spreadsheet_location"), props.getProperty("webdriver.chrome.driver"),
					props.getProperty("classes"), props.getProperty("spreadsheet_name"), props.getProperty("worksheet_caption"));

			config_properties.extract_class_links(props);
 
			result = "Classes: " + config_properties.getClasses() + 
						"\nSpreadsheet updater located at " + config_properties.getFill_spreadsheet_location() + 
						"\nLearn VCS URL: " + config_properties.getLogin_destination();
			System.out.println(result + "\nProgram Ran on " + datetime + " by user=" + System.getProperty("user.name"));
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return config_properties;
	}
}