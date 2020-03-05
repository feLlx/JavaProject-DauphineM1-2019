package io.github.JavaProject_DauphineM1_2019.tasks.anonymize;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.github.JavaProject_DauphineM1_2019.tasks.ATasks;

public class AnonymizeData extends ATasks{

	public AnonymizeData() {
	}

	/**
	 * This method anonymize a line of the csv file
	 * 
	 * @param fields	not <code>null</code>
	 * @param data		not <code>null</code>
	 */
	public void executeTask(String[] fields, String[] data){
		List<String> keyList = new ArrayList<String>(super.getContentJson().keySet());
		Collections.reverse(keyList);
		String result = ""; 

		for (int j = 0; j < keyList.size(); j++) {
			List<Method> listMethod = super.getContentMethod().get(keyList.get(j));
			List<String> header_= Arrays.asList(fields);
			final int placeInHeader= header_.indexOf(keyList.get(j));
			for(Method met:listMethod){
				try {					
					result+=met.invoke(super.getRulesInstance(), data[placeInHeader]) + ",";
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}		
		}
		String[] resultTab = result.split(",");
		super.getContentFileCsv().add(resultTab);
	}
}
