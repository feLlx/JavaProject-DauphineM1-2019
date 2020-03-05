package io.github.JavaProject_DauphineM1_2019.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.github.JavaProject_DauphineM1_2019.rules.Rules;

class RulesTest {
	
	Rules r = new Rules();

	/**
	 * this method test STRING method in Rules class
	 */
	@Test
	void StringTest() {
		assertTrue(r.STRING("String Value"));
		assertTrue(r.STRING("1"));
	}
	
	/**
	 * this method test INT method in Rules class
	 */
	@Test
	void IntTest() {
		assertFalse(r.INT("String Value"));
		assertTrue(r.INT("1"));
	}
	
	/**
	 * this method test DOUBLE method in Rules class
	 */
	@Test
	void DoubleTest() {
		assertFalse(r.DOUBLE("String Value"));
		assertTrue(r.DOUBLE("1"));
		assertTrue(r.DOUBLE("12.F"));
	}
	
	/**
	 * this method test BE_AN_AGE method in Rules class
	 */
	@Test
	void BeAnAgeTest() {
		assertFalse(r.BE_AN_AGE("String Value"));
		assertFalse(r.BE_AN_AGE("-1"));
		assertTrue(r.BE_AN_AGE("18"));
		assertFalse(r.BE_AN_AGE("120"));
	}
	
	/**
	 * this method test BE_AN_ADULT method in Rules class
	 */
	@Test
	void BeAnAdultTest() {
		assertFalse(r.BE_AN_ADULT("String Value"));
		assertFalse(r.BE_AN_ADULT("5"));
		assertTrue(r.BE_AN_ADULT("18"));
	}
	
	/**
	 * this method test BE_AN_ADULT method in Rules class
	 */
	@Test
	void BeAnAdultUSATest() {
		assertFalse(r.BE_AN_ADULT_USA("String Value"));
		assertFalse(r.BE_AN_ADULT_USA("5"));
		assertFalse(r.BE_AN_ADULT_USA("18"));
		assertTrue(r.BE_AN_ADULT_USA("21"));
	}
	
	/**
	 * this method test BE_AN_EMAIL method in Rules class
	 */
	@Test
	void BeAnEmailTest() {
		assertTrue(r.BE_AN_EMAIL("test@gmail.com"));
		assertTrue(r.BE_AN_EMAIL("xxx@yyy.zzz"));
	}
	
	/**
	 * this method test BE_AN_DAUPHINE_EMAIL method in Rules class
	 */
	@Test
	void BeAnDauphineEmailTest() {
		assertFalse(r.BE_AN_DAUPHINE_EMAIL("test@gmail.com"));
		assertFalse(r.BE_AN_DAUPHINE_EMAIL("xxx@yyy.zzz"));
		assertTrue(r.BE_AN_DAUPHINE_EMAIL("test@dauphine.eu"));
		assertTrue(r.BE_AN_DAUPHINE_EMAIL("test@dauphine.psl.eu"));
		assertTrue(r.BE_AN_DAUPHINE_EMAIL("test@lamsade.dauphine.fr"));
	}
	
	/**
	 * this method test BE_A_PHONE_NUMBER method in Rules class
	 */
	@Test
	void BeAPhoneNumberTest() {
		assertFalse(r.BE_A_PHONE_NUMBER("String value"));
		assertTrue(r.BE_A_PHONE_NUMBER("0606060606"));
		assertFalse(r.BE_A_PHONE_NUMBER("+706060606"));
	}
	
	/**
	 * this method test SALARY_MORE_THAN_FRENCH_SMIC method in Rules class
	 */
	@Test
	void SalaryFrenchSmicrTest() {
		assertFalse(r.SALARY_MORE_THAN_FRENCH_SMIC("String value"));
		assertFalse(r.SALARY_MORE_THAN_FRENCH_SMIC("900"));
		assertTrue(r.SALARY_MORE_THAN_FRENCH_SMIC("1300"));
	}
	
	/**
	 * this method test RANDOM_LETTER method in Rules class
	 */
	@Test
	void RandomLetterTest() {
		String anoVar = r.RANDOM_LETTER("Test Variable");
		assertEquals(anoVar, r.RANDOM_LETTER("Test Variable"));
	}
	
	/**
	 * this method test RANDOM_LETTER_FOR_LOCAL_PART method in Rules class
	 */
	@Test
	void RandomLetterForLocalPartTest() {
		assertEquals("Test Variable", r.RANDOM_LETTER_FOR_LOCAL_PART("Test Variable"));
		String anoVar = r.RANDOM_LETTER("test");
		assertEquals(anoVar + "@gmail.com", r.RANDOM_LETTER_FOR_LOCAL_PART("test@gmail.com"));
	}
	
	/**
	 * this method test CONFIDENTIAL method in Rules class
	 */
	@Test
	void ConfidentialTest() {
		assertEquals("confidential", r.CONFIDENTIAL("Test Variable"));
	}
	
	/**
	 * this method test NATIONALITY method in Rules class
	 */
	@Test
	void NationalityTest() {
		assertEquals("foreign", r.NATIONALITY("Test Variable"));
		assertEquals("french", r.NATIONALITY("french"));
	}

}
