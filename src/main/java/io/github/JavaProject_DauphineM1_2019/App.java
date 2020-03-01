package io.github.JavaProject_DauphineM1_2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.JavaProject_DauphineM1_2019.checkingData.CheckAndWriteData;

public class App {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 

	public static void main(String[] args) throws Exception {
		new App().proceed();
	}

	public void proceed() throws IOException {
		LOGGER.info("Hello user !");
		LOGGER.info("In this app you can either :\n" +
				"1 - Check if a csv file respects the rules indicated in a json file\n" +
				"2 - Anonymize the data in a csv file according to the rules indicated in a json file \n");
		LOGGER.info("Please enter 1 or 2 depending on the task you wish to perform");
		String input = in.readLine();
		if (Integer.parseInt(input) == 1) {
			LOGGER.info("Please enter in this order and separated : \n"
					+ "file name and extension of input file \n"
					+ "file name and extension of description file \n"
					+ "file name and extension of rule file \n"
					+ "file name and extension of output file \n");
			String[] line = new String[4]; 
			line = in.readLine().split(" "); 
			String inputFile = line[0];  
			String descFile = line[1];  
			String ruleFile = line[2];  
			String outputFile = line[3];
			
			CheckAndWriteData cw = new CheckAndWriteData(inputFile, descFile, ruleFile, outputFile);
			LOGGER.info("Application closed");
		}
		else if (Integer.parseInt(input) == 2) {
			LOGGER.info("Feature not develop yet");
		}
		else {
			LOGGER.info("Application closed");
		}
	}
}
