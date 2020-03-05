package io.github.JavaProject_DauphineM1_2019.tasks.anonymize;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


class AnonymizeDataTest {

	AnonymizeData ad = new AnonymizeData();

	/**
	 * this method test readCsv method in ATasks class when concrete class is AnonymizeData with csv file
	 */
	@Test
	void getContentFileCsvCheckTest() {
		ad.getInvokeMethod("AnonymizationRules.json", "name", "changeTo");
		ad.readCsv("listeEtudiants.csv", "", ",", false);
		String[] ExpectedLine = "NOM,EMAIL_PERSO".split(",");
		assertEquals(Arrays.toString(ExpectedLine), Arrays.toString(ad.getContentFileCsv().get(0)));
	}
	
	/**
	 * this method test readCsv method in ATasks class when concrete class is AnonymizeData with tsv file
	 */
	@Test
	void getContentFileTsvCheckTest() {
		ad.getInvokeMethod("AnonymizationRules.json", "name", "changeTo");
		ad.readCsv("listeEtudiants.tsv", "", "\t", false);
		String[] ExpectedLine = "NOM,EMAIL_PERSO".split(",");
		assertEquals(Arrays.toString(ExpectedLine), Arrays.toString(ad.getContentFileCsv().get(0)));
	}

}
