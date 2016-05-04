package database.quandl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import database.Database;

public class IMFCrossCountryMacroeconomicStatisticsTest {
	
	private String[][] expectedDataSet = new String[][] {
		{"PBANSOP_USD", "Bananas"},
		{"SWE_LE"	  , "Sweden-Employment"},
		{"SWE_LP"     , "Sweden Population"},
	};
	
	@Test
	public void getLinkToDatabaseCode() throws Exception {
		assertEquals("ODA", new IMFCrossCountryMacroeconomicStatistics().link());
	}
	
	@Test
	public void getDataSetsFromDatabase() throws Exception {
		assertArrayEquals(expectedDataSet, new IMFCrossCountryMacroeconomicStatistics().dataSet());
	}
	
	@Test
	public void getDataSourceFromDatabase() throws Exception {
		Database db = new IMFCrossCountryMacroeconomicStatistics();
		assertNotNull(db.getSource("SWE_LE"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void throwsExceptionWhenDataSourceDoesNotExistInDataBase() throws Exception {
		new IMFCrossCountryMacroeconomicStatistics().getSource("NEJ");
	}
	
}
