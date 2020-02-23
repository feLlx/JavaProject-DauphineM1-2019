package io.github.JavaProject_DauphineM1_2019.checkingData;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class CheckAndWriteDataTest {

	@Test
	void getInvokeMethodTest() throws ClassNotFoundException, NoSuchMethodException, SecurityException {
		CheckAndWriteData cw = new CheckAndWriteData();
		HashMap<String, ArrayList<Method>> contentMethodTest = new HashMap<String, ArrayList<Method>>();
		ArrayList<Method> listMethod = new ArrayList<Method>();
		
		Class<?> classWithMethod = Class.forName(cw.getRulesInstance().getClass().getCanonicalName());
		Method method = classWithMethod.getDeclaredMethod("STRING", String.class);
		listMethod.add(method);
		contentMethodTest.put("NOM", listMethod);
		contentMethodTest.put("DATE_DE_NAISSANCE", listMethod);
		contentMethodTest.put("EMAIL_PRO", listMethod);
		contentMethodTest.put("EMAIL_PERSO", listMethod);
		ArrayList<Method> listMethod2 = new ArrayList<Method>();
		Method method2 = classWithMethod.getDeclaredMethod("INT", String.class);
		listMethod2.add(method2);
		contentMethodTest.put("AGE", listMethod2);

		cw.getInvokeMethod("ObjectsDescription.json", "name", "dataType");

		assertEquals(true, cw.getContentMethod().containsKey("NOM"));
		assertEquals(contentMethodTest.get("NOM"), cw.getContentMethod().get("NOM"));
		assertEquals(true, cw.getContentMethod().containsKey("AGE"));
		assertEquals(contentMethodTest.get("AGE"), cw.getContentMethod().get("AGE"));
		assertEquals(true, cw.getContentMethod().containsKey("DATE_DE_NAISSANCE"));
		assertEquals(contentMethodTest.get("DATE_DE_NAISSANCE"), cw.getContentMethod().get("DATE_DE_NAISSANCE"));
		assertEquals(true, cw.getContentMethod().containsKey("EMAIL_PRO"));
		assertEquals(contentMethodTest.get("EMAIL_PRO"), cw.getContentMethod().get("EMAIL_PRO"));
		assertEquals(true, cw.getContentMethod().containsKey("EMAIL_PERSO"));
		assertEquals(contentMethodTest.get("EMAIL_PRO"), cw.getContentMethod().get("EMAIL_PRO"));

	}

}
