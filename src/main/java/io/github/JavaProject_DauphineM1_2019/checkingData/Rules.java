package io.github.JavaProject_DauphineM1_2019.checkingData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rules {

	public boolean STRING(String data) {
		return data instanceof String;
	}
	
	public static boolean INT(String data) {
		try {
			Integer.parseInt(data);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static boolean BE_AN_AGE(String data) {
		int value = Integer.parseInt(data);
		return value > 1 && value < 100;
	}
	
	public static boolean BE_AN_EMAIL(String data) {
		Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(data);
		return mat.matches();
	}
	
	public static boolean BE_AN_DAUPHINE_EMAIL(String data) {
		Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[dauphine]+(\\.[A-Za-z0-9]+)*+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(data);
		return mat.matches();
	}

}
