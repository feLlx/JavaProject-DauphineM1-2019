package io.github.JavaProject_DauphineM1_2019.rules;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.hash.Hashing;

public class Rules {

	/**
	 * This method check if the parameter is a String
	 * 
	 * @param data
	 * @return boolean
	 */
	public boolean STRING(String data) {
		return data instanceof String;
	}

	/**
	 * This method check if the parameter is an Integer
	 * 
	 * @param data
	 * @return boolean
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
	 * @return boolean
	 */
	public boolean BE_AN_AGE(String data) {
		return this.INT(data) && Integer.parseInt(data) > 0 && Integer.parseInt(data) < 120;
	}

	/**
	 * This method check if the parameter respect the regEx of a mail (XXX@YYY.ZZ)
	 * 
	 * @param data
	 * @return boolean
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
	 * @return boolean
	 */
	public boolean BE_AN_DAUPHINE_EMAIL(String data) {
		String regexDauphine = "^(([\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(dauphine.eu|dauphine.psl.eu|lamsade.dauphine.fr)))$";
		Pattern pattern = Pattern.compile(regexDauphine);
		Matcher mat = pattern.matcher(data);
		return mat.matches();
	}

	/**
	 * This method replace each letter of a string with another letter
	 * with the help of https://mkyong.com/java/java-md5-hashing-example/ And https://www.baeldung.com/sha-256-hashing-java
	 * 
	 * @param data
	 * @return String with random letter
	 */
	public String RANDOM_LETTER(String data) {
		return Hashing.sha256().hashString(data, StandardCharsets.UTF_8).toString().substring(0, data.length());
	}

	/**
	 * This method replace each letter of the first part of a mail with another letter
	 * 
	 * @param data
	 * @return data with the first part with random letter or data if data is not an email
	 */
	public String RANDOM_LETTER_FOR_LOCAL_PART(String data){
		if (this.BE_AN_EMAIL(data)) {
			String beginningString = data.substring(0, data.indexOf("@"));
			String endString = data.substring(data.indexOf("@"));
			return RANDOM_LETTER(beginningString) + endString;
		}
		else
			return data;
	}

}
