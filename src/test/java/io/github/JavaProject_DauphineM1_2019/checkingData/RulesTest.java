package io.github.JavaProject_DauphineM1_2019.checkingData;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
	void BeAnDauphineEmailtest() {
		assertFalse(r.BE_AN_DAUPHINE_EMAIL("test@gmail.com"));
		assertFalse(r.BE_AN_DAUPHINE_EMAIL("xxx@yyy.zzz"));
		assertTrue(r.BE_AN_DAUPHINE_EMAIL("test@dauphine.eu"));
		assertTrue(r.BE_AN_DAUPHINE_EMAIL("test@dauphine.psl.eu"));
		assertTrue(r.BE_AN_DAUPHINE_EMAIL("test@lamsade.dauphine.fr"));
	}

}
