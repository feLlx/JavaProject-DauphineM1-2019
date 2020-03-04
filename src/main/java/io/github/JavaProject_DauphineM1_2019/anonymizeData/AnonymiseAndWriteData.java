package io.github.JavaProject_DauphineM1_2019.anonymizeData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import au.com.bytecode.opencsv.CSVWriter;
import io.github.JavaProject_DauphineM1_2019.Rules.Rules;
import io.github.JavaProject_DauphineM1_2019.json.ReadJson;

public class AnonymiseAndWriteData {
	
	private HashMap<String, ArrayList<String>> contentJson = new HashMap<String, ArrayList<String>>();
	private HashMap<String, ArrayList<Method>> contentMethod = new HashMap<String, ArrayList<Method>>();
	private List<String[]> contentFileCsv =  new ArrayList<String[]>(); 
	private Rules rulesInstance = new Rules();
	private String[] fields;

	
	//those getters are created for the CheckAndWriteTest class
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

		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			while ((lineSeparator = br.readLine()) != null) {
				//first line is the header with fields name
				if (header == false) {
					fields = lineSeparator.split(dataSeparator);
					header = true;
				}
				else {
					String[] data = lineSeparator.split(dataSeparator);
					anonymize(data);
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
	 * This method anonymize a line of the csv file
	 * 
	 * @param data		not <code>null</code>
	 */
	private void anonymize(String[] data){
		List<String> keyList = new ArrayList<String>(contentJson.keySet());
		Collections.reverse(keyList);
		String result = ""; 

		for (int j = 0; j < keyList.size(); j++) {
			List<Method> listMethod = contentMethod.get(keyList.get(j));
			List<String> header_= Arrays.asList(fields);
			final int placeInHeader= header_.indexOf(keyList.get(j));
			for(Method met:listMethod){
				try {					
					result+=met.invoke(rulesInstance, data[placeInHeader]);
					if (j != contentMethod.size() - 1)
						result += ",";
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}		
		}
		String[] resultTab = result.split(",");
		contentFileCsv.add(resultTab);
		for(int i=0;i<resultTab.length;++i){
			System.out.println(resultTab[i]);
		}
	}
	
	public AnonymiseAndWriteData(){
		getInvokeMethod("AnonymisationRules.json","name","changeTo");
		readCsv("listeEtudiants.csv", "", ",", false);
	}

}
