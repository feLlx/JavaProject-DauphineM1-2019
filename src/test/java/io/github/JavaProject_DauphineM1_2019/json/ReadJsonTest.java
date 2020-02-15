package io.github.JavaProject_DauphineM1_2019.json;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;

class ReadJsonTest {

	@SuppressWarnings("unchecked")
	@Test
	void test() {
		String nameFile = "AnonymisationRules.json";
		ArrayList<String> nameList = new ArrayList<>();
		ArrayList<String> dataList = new ArrayList<>();

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		File file = new File(classLoader.getResource(nameFile).getFile());

		JSONParser jsonParser = new JSONParser();

		try {
			FileReader reader = new FileReader(file);
			Object obj = jsonParser.parse(reader);
			JSONArray jsonArray = (JSONArray) obj;

			jsonArray.forEach(type -> {
				JSONObject o = (JSONObject) type;
				nameList.add((String) o.get("name"));
				dataList.add((String) o.get("changeTo"));
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals("NOM", nameList.get(0));
		assertEquals("RANDOM_LETTER", dataList.get(0));
		assertEquals("EMAIL_PERSO", nameList.get(1));
		assertEquals("RANDOM_LETTER_FOR_LOCAL_PART", dataList.get(1));
	}
}

