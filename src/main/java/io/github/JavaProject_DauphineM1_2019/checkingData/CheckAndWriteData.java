package io.github.JavaProject_DauphineM1_2019.checkingData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

import io.github.JavaProject_DauphineM1_2019.App;
import io.github.JavaProject_DauphineM1_2019.json.ReadJson;

public class CheckAndWriteData {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	private HashMap<String, ArrayList<String>> contentDescription = new HashMap<String, ArrayList<String>>();
	private HashMap<String, Method> contentMethod= new HashMap<String, Method>();
	private HashMap<Integer, List<String>> contentFileCsv= new HashMap<Integer, List<String>>();
	private static final AtomicInteger count = new AtomicInteger(0);
	private Rules rulesInstance = new Rules();

	public void getInvokeMethod(String fileName) {
		contentDescription = ReadJson.getInfosFromJSON("ObjectsDescription.json", "name", "dataType");
		for (Entry<String, ArrayList<String>> entry : contentDescription.entrySet()) {
			for (int i = 0; i < entry.getValue().size(); i ++) {
				try {
					Class<?> classWithMethod = Class.forName(rulesInstance.getClass().getCanonicalName());
					Method method = classWithMethod.getDeclaredMethod(entry.getValue().get(i), String.class);
					contentMethod.put(entry.getKey().toString(), method);
				} catch (NoSuchMethodException | SecurityException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void isRecordTrue(String[] fields, String[] data) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<Boolean> allTrue = new ArrayList<Boolean>();
		for (int j = 0; j < contentDescription.size(); j++) {
			if ( (boolean) contentMethod.get(fields[j]).invoke(rulesInstance, data[j]) ) {
				allTrue.add(true);
			}
			else {
				allTrue.add(false);
			}
		}
		int compteur = 0;
		for (int k = 0; k < allTrue.size(); k++) {
			if (allTrue.get(k) == true) {
				compteur++;
			}
		}
		if (compteur == allTrue.size()) {
			List<String> result = new ArrayList<String>();
			for (int l = 0; l < compteur; l++) {
				result.add(data[l]);
			}
			contentFileCsv.put(count.incrementAndGet() , result);
		}
		allTrue = new ArrayList<Boolean>();
	}

	public CheckAndWriteData(String fileName) {

		getInvokeMethod(fileName);

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		String[] fields = null;
		String[] data = null;
		boolean header = false;


		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				if (header == false) {
					fields = line.split(cvsSplitBy);
					header = true;
				}
				else {
					data = line.split(cvsSplitBy);
					isRecordTrue(fields, data);
				}
			}

		} catch (IOException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		for (Entry<Integer, List<String>> entry : contentFileCsv.entrySet()) {
			LOGGER.info("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
		}

	}

	public static void main(String[] args) throws Exception {
		@SuppressWarnings("unused")
		CheckAndWriteData c = new CheckAndWriteData("listeEtudiants.csv");
	}

}
