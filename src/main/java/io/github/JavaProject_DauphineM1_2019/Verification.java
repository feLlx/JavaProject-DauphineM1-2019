package io.github.JavaProject_DauphineM1_2019;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;

public class Verification {
	private HashMap<String, ArrayList<String>> verif;

	public Verification() {
		verif = new HashMap<String, ArrayList<String>>();
	}

	// https://www.javaguides.net/2019/07/jsonsimple-tutorial-read-and-write-json-in-java.html
	@SuppressWarnings("unchecked")
	void getInfosFromJSON(String nomFichier) {
		String file = "C:/Users/Alison/Desktop/Formation/M1 MIAGE/Java/project/src/main/resources/"+nomFichier+".json";
		JSONParser jsonParser = new JSONParser();
		JSONArray jsonArray = null;

		// Read JSON file and convert to java object
		try {
			FileReader reader = new FileReader(file);
			verif.clear();
			Object obj = jsonParser.parse(reader);
			jsonArray = (JSONArray) obj;


			// Iterate over type array
			jsonArray.forEach(type -> {
				if (type instanceof JSONObject) {
					JSONObject o = (JSONObject) type;

					if (nomFichier.contains("Description")) {
						if ((o.get("name") instanceof String) && (o.get("dataType") instanceof String)) {
							ArrayList<String>l=new ArrayList<>();
							l.add((String) o.get("dataType"));
							verif.put((String) o.get("name"), l);
						}
					}

					if (nomFichier.contains("Verification")) {
						if ((o.get("name") instanceof String) && (o.get("should") instanceof JSONArray)) {
							ArrayList<String>l=new ArrayList<>();
							JSONArray listVerif = (JSONArray) o.get("should");
							listVerif.forEach(ele -> {
								l.add((String) ele);
							});
							verif.put((String) o.get("name"),l);	
						}
					}

					if (nomFichier.contains("Anonymisation")) {
						if ((o.get("name") instanceof String) && (o.get("changeTo") instanceof String)){
							ArrayList<String>l=new ArrayList<>();
							l.add((String) o.get("changeTo"));
							verif.put((String) o.get("name"), l);
						}
					}
				}
			});
			System.out.println(verif);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
