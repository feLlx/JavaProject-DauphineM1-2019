package io.github.JavaProject_DauphineM1_2019.tasks.check;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class CheckDataTest {
	
	CheckData cw = new CheckData();

	/**
	 * this method test readCsv method in ATasks class when concrete class is CheckData
	 */
	@Test
	void getContentFileCsvCheckTest() {
		cw.getInvokeMethod("ObjectsDescription.json", "name", "dataType");
		cw.getInvokeMethod("VerificationRules.json", "name", "should");
		cw.readCsv("listeEtudiants.csv", "", ",", false);
		String[] ExpectedLine = "NOM,AGE,DATE_DE_NAISSANCE,EMAIL_PRO,EMAIL_PERSO".split(",");
		assertEquals(Arrays.toString(ExpectedLine), Arrays.toString(cw.getContentFileCsv().get(0)));
		ExpectedLine = "John,22,01/01/1998,john.smith@dauphine.eu,jhon.smith@gmail.com".split(",");
		assertEquals(Arrays.toString(ExpectedLine), Arrays.toString(cw.getContentFileCsv().get(1)));
		ExpectedLine = "Will,24,28/02/1995,will.huv@dauphine.eu,willhuv1@hotmail.com".split(",");
		assertEquals(Arrays.toString(ExpectedLine), Arrays.toString(cw.getContentFileCsv().get(2)));
	}

}
