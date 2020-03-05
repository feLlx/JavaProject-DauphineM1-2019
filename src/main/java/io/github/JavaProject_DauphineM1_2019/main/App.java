package io.github.JavaProject_DauphineM1_2019.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.JavaProject_DauphineM1_2019.tasks.anonymize.AnonymizeData;
import io.github.JavaProject_DauphineM1_2019.tasks.check.CheckData;

public class App {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 

	public static void main(String[] args) throws Exception {
		new App().proceed();
	}

	public void proceed() throws IOException {
		LOGGER.info("Hello user ! \n" + 
				"In this app you can either :\n" +
				"1 - Check if a csv file respects the rules indicated in a json file\n" +
				"2 - Anonymize the data in a csv file according to the rules indicated in a json file \n" +
				"3 - Check and anonymize at the same time \n");
		LOGGER.info("Please enter 1, 2 or 3 depending on the task you wish to perform");
		String input = in.readLine();
		try {
			if (Integer.parseInt(input) == 1 || Integer.parseInt(input) == 2 || Integer.parseInt(input) == 3) {
				String message = "Please enter in this order and separated with space : \n"
						+ "file name and extension of input file (should be of type csv) \n"
						+ "file name and extension of description file (should be json file) \n";
				if (Integer.parseInt(input) != 3)
					message += "file name and extension of rule file (should be json file) \n";
				else
					message += "file name and extension of rule file for checking (should be json file) \n"
							+ "file name and extension of rule file for anonymization (should be json file) \n";
				message += "file name and extension of output file (should be csv file) \n";
				
				LOGGER.info(message);
				LOGGER.info("Precise the separatorof your input file. For a csv enter a comma.");
				String[] line = new String[6]; 
				line = in.readLine().split(" "); 
				String inputFile = line[0];  
				String descFile = line[1];
				String ruleFile = line[2];
				String outputFile = line[3];
				String separator = line[4];

				if (Integer.parseInt(input) == 1) {
					CheckData cd = new CheckData();	
					cd.getInvokeMethod(descFile, "name", "dataType");
					cd.getInvokeMethod(ruleFile, "name", "should");
					cd.readCsv(inputFile, "", separator, false);
					cd.writeCsv(outputFile);
				}
				else if (Integer.parseInt(input) == 2) {
					AnonymizeData ad = new AnonymizeData();	
					ad.getInvokeMethod(ruleFile,"name","changeTo");
					ad.readCsv(inputFile, "", separator, false);
					ad.writeCsv(outputFile);
				}
				else if (Integer.parseInt(input) == 3) {
					String checkRuleFile = line[2];  
					String anonymRuleFile = line[3]; 
					outputFile = line[4];
					separator = line[5];

					CheckData cd = new CheckData();
					cd.getInvokeMethod(descFile, "name", "dataType");
					cd.getInvokeMethod(checkRuleFile, "name", "should");
					cd.readCsv(inputFile, "", separator, false);
					AnonymizeData ad = new AnonymizeData();	
					ad.getInvokeMethod(anonymRuleFile,"name","changeTo");
					String temp ="";
					String[] fieldsTemp = cd.getContentFileCsv().get(0);
					for (int j = 0; j < fieldsTemp.length; j ++) {
						if (ad.getContentMethod().containsKey(fieldsTemp[j]))
							temp += fieldsTemp[j] + ",";
					}
					String[] fields = temp.split(",");
					ad.getContentFileCsv().add(fields);
					for (int i = 1; i < cd.getContentFileCsv().size(); i ++) {
						String[] data = cd.getContentFileCsv().get(i);
						ad.executeTask(fieldsTemp, data);
					}
					ad.writeCsv(outputFile);
				}
				LOGGER.info("Success");
				LOGGER.info("Application closed");
			}
			else {
				LOGGER.error("Incorrect. Application closed");
			}
		} catch (Exception e) {
			LOGGER.error("Failed. Application closed");
		}
	}
}
