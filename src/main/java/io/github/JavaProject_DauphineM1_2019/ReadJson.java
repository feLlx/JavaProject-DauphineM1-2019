package io.github.JavaProject_DauphineM1_2019;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

public class ReadJson {

	// https://www.javaguides.net/2019/07/jsonsimple-tutorial-read-and-write-json-in-java.html
	@SuppressWarnings("unchecked")
	public static HashMap<String, ArrayList<String>> getInfosFromJSON(String nomFichier, String key1, String key2) {
		HashMap<String, ArrayList<String>> content = new HashMap<String, ArrayList<String>>();

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		File file = new File(classLoader.getResource(nomFichier).getFile());

		JSONParser jsonParser = new JSONParser();

		// Read JSON file and convert to java object
		try {
			FileReader reader = new FileReader(file);
			Object obj = jsonParser.parse(reader);
			JSONArray jsonArray = (JSONArray) obj;

			// Iterate over type array
			jsonArray.forEach(type -> {
				JSONObject o = (JSONObject) type;
				ArrayList<String>valueList=new ArrayList<>();
				if (o.get(key2) instanceof JSONArray) {
					JSONArray listVerif = (JSONArray) o.get(key2);
					listVerif.forEach(ele -> {
						valueList.add((String) ele);
					});
				}
				else valueList.add((String) o.get(key2));
				content.put((String) o.get(key1), valueList);
			});
			System.out.println(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
}