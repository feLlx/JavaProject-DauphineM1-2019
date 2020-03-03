package io.github.JavaProject_DauphineM1_2019.checkingData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rules {

	public boolean STRING(String data) {
		return data instanceof String;
	}
	
	public boolean INT(String data) {
		try {
			Integer.parseInt(data);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean BE_AN_AGE(String data) {
		try {
			int value = Integer.parseInt(data);
			return value > 1 && value < 100;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean BE_AN_EMAIL(String data) {
		String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher mat = pattern.matcher(data);
		return mat.matches();
	}
	
	public boolean BE_AN_DAUPHINE_EMAIL(String data) {
		String regexDauphine = "^(([\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@dauphine.eu)|([\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@dauphine.psl.eu))$";
		Pattern pattern = Pattern.compile(regexDauphine);
		Matcher mat = pattern.matcher(data);
		return mat.matches();
	}

}
