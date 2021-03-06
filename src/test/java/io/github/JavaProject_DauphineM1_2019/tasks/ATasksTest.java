package io.github.JavaProject_DauphineM1_2019.tasks;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import io.github.JavaProject_DauphineM1_2019.tasks.check.CheckData;

class ATasksTest {

	CheckData cw = new CheckData();

	/**
	 * this method test getInvokeMethod method in ATasks class
	 * 
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@Test
	void getInvokeMethodTest() throws ClassNotFoundException, NoSuchMethodException, SecurityException {
		Class<?> classWithMethod = Class.forName(cw.getRulesInstance().getClass().getCanonicalName());

		ArrayList<ArrayList<Method>> listAllMethod = new ArrayList<ArrayList<Method>>();
		ArrayList<Method> listMethodNameAndBirth = new ArrayList<Method>();
		ArrayList<Method> listMethodAge = new ArrayList<Method>();
		ArrayList<Method> listMethodEmail = new ArrayList<Method>();
		ArrayList<Method> listMethodEmailPro = new ArrayList<Method>();

		Method method = classWithMethod.getDeclaredMethod("STRING", String.class);
		listMethodNameAndBirth.add(method);
		listMethodEmailPro.add(method);
		listMethodEmail.add(method);
		listAllMethod.add(listMethodNameAndBirth);
		method = classWithMethod.getDeclaredMethod("INT", String.class);
		listMethodAge.add(method);
		listAllMethod.add(listMethodAge);
		method = classWithMethod.getDeclaredMethod("BE_AN_AGE", String.class);
		listMethodAge.add(method);
		method = classWithMethod.getDeclaredMethod("BE_AN_EMAIL", String.class);
		listMethodEmailPro.add(method);
		listMethodEmail.add(method);
		listAllMethod.add(listMethodEmail);
		method = classWithMethod.getDeclaredMethod("BE_AN_DAUPHINE_EMAIL", String.class);
		listMethodEmailPro.add(method);
		listAllMethod.add(listMethodEmailPro);

		cw.getInvokeMethod("ObjectsDescription.json", "name", "dataType");
		cw.getInvokeMethod("VerificationRules.json", "name", "should");

		assertEquals(true, cw.getContentMethod().containsKey("NOM"));
		assertEquals(true, cw.getContentMethod().containsKey("AGE"));
		assertEquals(true, cw.getContentMethod().containsKey("DATE_DE_NAISSANCE"));
		assertEquals(true, cw.getContentMethod().containsKey("EMAIL_PRO"));
		assertEquals(true, cw.getContentMethod().containsKey("EMAIL_PERSO"));

		assertEquals(listAllMethod.get(0), cw.getContentMethod().get("NOM"));
		assertEquals(listAllMethod.get(1), cw.getContentMethod().get("AGE"));
		assertEquals(listAllMethod.get(0), cw.getContentMethod().get("DATE_DE_NAISSANCE"));
		assertEquals(listAllMethod.get(3), cw.getContentMethod().get("EMAIL_PRO"));
		assertEquals(listAllMethod.get(2), cw.getContentMethod().get("EMAIL_PERSO"));
	}

	/**
	 * this method test writeCsv method in ATasks class
	 */
	@Test
	void WriteCsvTest() {
		cw.getInvokeMethod("ObjectsDescription.json", "name", "dataType");
		cw.getInvokeMethod("VerificationRules.json", "name", "should");
		cw.readCsv("listeEtudiants.csv", "", ",", false);
		cw.writeCsv("listVerifEtu.csv");
		String file = "src/main/resources/listVerifEtu.csv";
		File f = new File(file);
		if (f.delete())
			assertTrue(true);
		else
			assertTrue(false);
	}

}
