package io.github.JavaProject_DauphineM1_2019.json;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.github.JavaProject_DauphineM1_2019.ReadJson;

class ReadJsonTest {

	@Test
	void test() {		
		HashMap<String, ArrayList<String>> content = new HashMap<String, ArrayList<String>>();
		
		content = ReadJson.getInfosFromJSON("ObjectsDescription.json", "name", "dataType");
		
		List<String> valueList = new ArrayList<>();
		valueList.add("STRING");
		List<String> valueList2 = new ArrayList<>();
		valueList2.add("INT");
		
		assertEquals(true, content.containsKey("NOM"));
		assertEquals(valueList, content.get("NOM"));
		assertEquals(true, content.containsKey("AGE"));
		assertEquals(valueList2, content.get("AGE"));
		assertEquals(true, content.containsKey("DATE_DE_NAISSANCE"));
		assertEquals(valueList, content.get("DATE_DE_NAISSANCE"));
		assertEquals(true, content.containsKey("EMAIL_PRO"));
		assertEquals(valueList, content.get("EMAIL_PRO"));
		assertEquals(true, content.containsKey("EMAIL_PERSO"));
		assertEquals(valueList, content.get("EMAIL_PERSO"));
		
		content = ReadJson.getInfosFromJSON("VerificationRules.json", "name", "should");
		
		List<String> valueList3 = new ArrayList<>();
		valueList3.add("BE_AN_AGE");
		List<String> valueList4 = new ArrayList<>();
		valueList4.add("BE_AN_EMAIL");
		List<String> valueList5 = new ArrayList<>();
		valueList5.add("BE_AN_EMAIL");
		valueList5.add("BE_AN_DAUPHINE_EMAIL");
		
		assertEquals(true, content.containsKey("AGE"));
		assertEquals(valueList3, content.get("AGE"));
		assertEquals(true, content.containsKey("EMAIL_PRO"));
		assertEquals(valueList5, content.get("EMAIL_PRO"));
		assertEquals(true, content.containsKey("EMAIL_PERSO"));
		assertEquals(valueList4, content.get("EMAIL_PERSO"));
	}

}

