package servlet.helper;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class CheckIfAnalyzeDataIsValidTest {
	private CheckIfAnalyzeDataIsValid ciadv;
	
	@Before
	public void setUp() {
		ciadv = new CheckIfAnalyzeDataIsValid();
	}

	@Test
	public void testValidDate() {
		assertTrue(ciadv.validDate("0001-01-01"));
		assertTrue(ciadv.validDate("4000-01-01"));
		assertTrue(ciadv.validDate("2016-05-18"));
	}

	@Test
	public void testValidResulution() {
		assertTrue(ciadv.validResulution("DAY"));
		assertFalse(ciadv.validResulution("DAYYD"));
	}

	@Test
	public void testDatabaseExist() {
		assertTrue(ciadv.databaseExist("ODA"));
		assertFalse(ciadv.databaseExist("Database"));
	}

	@Test
	public void testDataSetExist() {
		assertTrue(ciadv.dataSetExist("ODA", "SWE_LP"));
		assertFalse(ciadv.dataSetExist("ODA", "SWE"));
	}
	@Test
	public void testIsAlphaNumeric() {
		assertTrue(ciadv.isAlphaNumeric("ODA"));
		assertTrue(ciadv.isAlphaNumeric("1234"));
		assertFalse(ciadv.isAlphaNumeric("123AD--"));
		assertFalse(ciadv.isAlphaNumeric("+++"));
	}
}
