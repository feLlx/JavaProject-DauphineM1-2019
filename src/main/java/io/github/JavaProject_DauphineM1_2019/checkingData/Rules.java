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
		String email= "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,4}$"; 
		//Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
		Pattern pattern = Pattern.compile(email);
		Matcher mat = pattern.matcher(data);
		return mat.matches();
	}
	
	public boolean BE_AN_DAUPHINE_EMAIL(String data) {
		String emailDauphine= "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@dauphine" + 
                "(\\.)+[a-z" + 
                "A-Z]{2,7}$"; 
		//Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@dauphine+(\\.[A-Za-z0-9]+)*+\\.[A-Za-z]{2,4}");
		Pattern pattern = Pattern.compile(emailDauphine);
		Matcher mat = pattern.matcher(data);
		return mat.matches();
	}

}
