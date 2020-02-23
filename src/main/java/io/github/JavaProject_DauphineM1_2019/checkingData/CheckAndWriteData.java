package io.github.JavaProject_DauphineM1_2019.checkingData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVWriter;
import io.github.JavaProject_DauphineM1_2019.App;
import io.github.JavaProject_DauphineM1_2019.json.ReadJson;

public class CheckAndWriteData {

	private HashMap<String, ArrayList<String>> contentJson = new HashMap<String, ArrayList<String>>();
	private HashMap<String, ArrayList<Method>> contentMethod = new HashMap<String, ArrayList<Method>>();
	private List<String[]> contentFileCsv =  new ArrayList<String[]>(); 
	private Rules rulesInstance = new Rules();

	public CheckAndWriteData(String inputFile, String descriptionFile, String rulesFile, String outputFile) {
		getInvokeMethod(descriptionFile, "name", "dataType");
		getInvokeMethod(rulesFile, "name", "should");
		readCsv(inputFile, "", ",", false);
		writeCsv(outputFile);
	}

	public void getInvokeMethod(String descriptionFile, String key1, String key2) {
		contentJson = ReadJson.getInfosFromJSON(descriptionFile, key1, key2);
		for (Entry<String, ArrayList<String>> entry : contentJson.entrySet()) {
			ArrayList<Method> listMethod = new ArrayList<Method>();
			if (contentMethod.containsKey(entry.getKey())) {
				listMethod = contentMethod.get(entry.getKey());
			}
			for (int i = 0; i < entry.getValue().size(); i ++) {
				try {
					Class<?> classWithMethod = Class.forName(rulesInstance.getClass().getCanonicalName());
					Method method = classWithMethod.getDeclaredMethod(entry.getValue().get(i), String.class);
					listMethod.add(method);
				} catch (NoSuchMethodException | SecurityException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			contentMethod.put(entry.getKey().toString(), listMethod);
		}
	}

	public void checkAllConditions(String[] fields, String[] data) {
		List<Boolean> allTrue = new ArrayList<Boolean>();
		for (int j = 0; j < contentMethod.size(); j++) {
			List<Method> listMethod = contentMethod.get(fields[j]);
			final int index = j;
			listMethod.stream().allMatch(i -> {
				try {
					allTrue.add((boolean) i.invoke(rulesInstance, data[index]));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
				return true;
			});
		}
		if (allTrue.stream().allMatch(i -> i == true)) {
			String result = ""; 
			for (int l = 0; l < contentMethod.size(); l++) {
				result += data[l];
				if (l != contentMethod.size() - 1)
					result += ",";
			}
			System.out.println(result);
			String[] resultTab = result.split(",");
			contentFileCsv.add(resultTab);
		}
	}

	// method realize thanks to : https://mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
	public void readCsv(String inputFile, String lineSeparator, String dataSeparator, boolean header) {

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		File file = new File(classLoader.getResource(inputFile).getFile());

		String[] fields = null;

		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			while ((lineSeparator = br.readLine()) != null) {
				if (header == false) {
					fields = lineSeparator.split(dataSeparator);
					header = true;
				}
				else {
					String[] data = lineSeparator.split(dataSeparator);
					checkAllConditions(fields, data);
				}
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeCsv(String outputFile) {
		String file = "src/main/resources/" + outputFile;
		try { 
			FileWriter outputfile = new FileWriter(file); 
			CSVWriter writer = new CSVWriter(outputfile); 
			writer.writeAll(contentFileCsv); 
			writer.close(); 
		} 
		catch (IOException e) { 
			e.printStackTrace(); 
		}
	}

}
