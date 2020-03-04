package io.github.JavaProject_DauphineM1_2019.Rules;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.RandomStringUtils;

import com.google.common.hash.Hashing;

import io.github.JavaProject_DauphineM1_2019.App;

public class Rules {

	/**
	 * This method check if the parameter is a String
	 * 
	 * @param data
	 * @return
	 */
	public boolean STRING(String data) {
		return data instanceof String;
	}

	/**
	 * This method check if the parameter is an Integer
	 * 
	 * @param data
	 * @return
	 */
	public boolean INT(String data) {
		try {
			Integer.parseInt(data);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * This method check if the parameter is an Integer and in the interval ]0;120[
	 * 
	 * @param data
	 * @return
	 */
	public boolean BE_AN_AGE(String data) {
		return this.INT(data) && Integer.parseInt(data) > 0 && Integer.parseInt(data) < 120;
	}

	/**
	 * This method check if the parameter respect the regEx of a mail (XXX@YYY.ZZ)
	 * 
	 * @param data
	 * @return
	 */
	public boolean BE_AN_EMAIL(String data) {
		String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher mat = pattern.matcher(data);
		return mat.matches();
	}

	/**
	 * This method check if the parameter respect the regEx of a dauphine mail
	 * (XXX@dauphine.eu or XXX@dauphine.psl.eu or XXX@lamsade.dauphine.fr)
	 * 
	 * @param data
	 * @return
	 */
	public boolean BE_AN_DAUPHINE_EMAIL(String data) {
		String regexDauphine = "^(([\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(dauphine.eu|dauphine.psl.eu|lamsade.dauphine.fr)))$";
		Pattern pattern = Pattern.compile(regexDauphine);
		Matcher mat = pattern.matcher(data);
		return mat.matches();
	}
	
	public String RANDOM_LETTER(String s) {
		return Hashing.sha256().hashString(s, StandardCharsets.UTF_8).toString().substring(0, s.length());
	}
	
	public String RANDOM_LETTER_FOR_LOCAL_PART(String stringToAnonymize){
	    String anonymization= stringToAnonymize.substring(0, stringToAnonymize.indexOf("@"));
	    String endString= stringToAnonymize.substring(stringToAnonymize.indexOf("@"));
	    return RANDOM_LETTER(anonymization) + endString;
	}
	
}
