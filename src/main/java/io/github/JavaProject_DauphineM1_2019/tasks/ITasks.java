package io.github.JavaProject_DauphineM1_2019.tasks;

public interface ITasks {
	
	void getInvokeMethod(String descriptionFile, String key1, String key2);
	
	void executeTask(String[] fields, String[] data);
	
	void readCsv(String inputFile, String lineSeparator, String dataSeparator, boolean header);
	
	void writeCsv(String outputFile);

}
