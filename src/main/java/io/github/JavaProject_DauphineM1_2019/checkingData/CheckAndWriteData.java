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

import au.com.bytecode.opencsv.CSVWriter;
import io.github.JavaProject_DauphineM1_2019.Rules.Rules;
import io.github.JavaProject_DauphineM1_2019.json.ReadJson;

public class CheckAndWriteData {

	private HashMap<String, ArrayList<String>> contentJson = new HashMap<String, ArrayList<String>>();
	private HashMap<String, ArrayList<Method>> contentMethod = new HashMap<String, ArrayList<Method>>();
	private List<String[]> contentFileCsv =  new ArrayList<String[]>(); 
	private Rules rulesInstance = new Rules();

	public CheckAndWriteData() {
	}
	
	//those getters are created for the CheckAndWriteTest class
	public HashMap<String, ArrayList<String>> getContentJson() {
		return contentJson;
	}
	public HashMap<String, ArrayList<Method>> getContentMethod() {
		return contentMethod;
	}
	public List<String[]> getContentFileCsv() {
		return contentFileCsv;
	}
	public Rules getRulesInstance() {
		return rulesInstance;
	}

	/**
	 * This method read a hashmap that contains method name per fields and store those methods in a hashmap.
	 * 
	 * @param descriptionFile		not <code>null</code>
	 * @param key1					not <code>null</code>
	 * @param key2					not <code>null</code>
	 */
	public void getInvokeMethod(String descriptionFile, String key1, String key2) {
		contentJson = ReadJson.getInfosFromJSON(descriptionFile, key1, key2);
		for (Entry<String, ArrayList<String>> entry : contentJson.entrySet()) {
			ArrayList<Method> listMethod = new ArrayList<Method>();
			// check if a key already exists and copy the existing method
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

	/**
	 * This method check if you can apply methods stock in a hashmap to the corresponding data.
	 * Add true or false in a tab depending on the result
	 * If there is all true, add datas to a list, if not ignore datas
	 * 
	 * @param fields		not <code>null</code>
	 * @param data			not <code>null</code>
	 */
	private void checkAllConditions(String[] fields, String[] data) {
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
			String[] resultTab = result.split(",");
			contentFileCsv.add(resultTab);
		}
	}

	/**
	 * This method read a csv and call method checkAllConditions(fields, data)
	 * method realize thanks to : https://mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
	 * 
	 * @param inputFile				not <code>null</code>
	 * @param lineSeparator			not <code>null</code>
	 * @param dataSeparator			not <code>null</code>
	 * @param header				not <code>null</code>
	 */
	public void readCsv(String inputFile, String lineSeparator, String dataSeparator, boolean header) {

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		File file = new File(classLoader.getResource(inputFile).getFile());

		String[] fields = null;

		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			while ((lineSeparator = br.readLine()) != null) {
				//first line is the header with fields name
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
	
	/**
	 * This method create and write data into a csv file
	 * Method write with the help of https://howtodoinjava.com/library/parse-read-write-csv-opencsv/
	 * 
	 * @param outputFile		not <code>null</code>
	 */
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
	
	/**
	 * This method execute all methods needed to perform the verification task.
	 * 
	 * @param inputFile					not <code>null</code>
	 * @param descriptionFile			not <code>null</code>
	 * @param rulesFile					not <code>null</code>
	 * @param outputFile				not <code>null</code>
	 */
	public CheckAndWriteData(String inputFile, String descriptionFile, String rulesFile, String outputFile) {
		getInvokeMethod(descriptionFile, "name", "dataType");
		getInvokeMethod(rulesFile, "name", "should");
		readCsv(inputFile, "", ",", false);
		writeCsv(outputFile);
	}
	
	
}
