package io.github.JavaProject_DauphineM1_2019.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.github.JavaProject_DauphineM1_2019.Rules.Rules;

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

}
