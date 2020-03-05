package io.github.JavaProject_DauphineM1_2019.tasks.check;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import io.github.JavaProject_DauphineM1_2019.tasks.ATasks;

public class CheckData extends ATasks {

	public CheckData() {
	}

	/**
	 * This method check if you can apply methods stock in a hashmap to the corresponding data.
	 * Add true or false in a tab depending on the result
	 * If there is all true, add datas to a list, if not ignore datas
	 * 
	 * @param fields		not <code>null</code>
	 * @param data			not <code>null</code>
	 */
	public void executeTask(String[] fields, String[] data) {
		List<Boolean> allTrue = new ArrayList<Boolean>();
		for (int j = 0; j < super.getContentMethod().size(); j++) {
			List<Method> listMethod = super.getContentMethod().get(fields[j]);
			final int index = j;
			listMethod.stream().allMatch(i -> {
				try {
					allTrue.add((boolean) i.invoke(super.getRulesInstance(), data[index]));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
				return true;
			});
		}
		if (allTrue.stream().allMatch(i -> i == true)) {
			String result = ""; 
			for (int l = 0; l < super.getContentMethod().size(); l++) {
				result += data[l] + ",";
			}
			String[] resultTab = result.split(",");
			super.getContentFileCsv().add(resultTab);
		}
	}

}
