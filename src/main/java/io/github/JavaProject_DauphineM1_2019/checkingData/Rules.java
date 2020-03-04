package io.github.JavaProject_DauphineM1_2019.checkingData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.RandomStringUtils;

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

	public String RANDOM_LETTER(String stringToAnonymize){
	    String anonymization="";
	    
	    for (char ch: stringToAnonymize.toCharArray()) {
		    if(Character.isLetter(ch)){
				anonymization+=RandomStringUtils.randomAlphabetic(1);	
		    }else{
		    	anonymization+=ch;
		    }
	    }
		return anonymization;		
	}
	
	public String RANDOM_LETTER_FOR_LOCAL_PART(String stringToAnonymize, Character symbol){
	    String anonymization= stringToAnonymize.substring(0, stringToAnonymize.indexOf(symbol.toString()));
		return RANDOM_LETTER(anonymization);
	}
	
}
