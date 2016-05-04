package database.quandl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import database.Database;

public class WorldBankWorldDevelopmentIndicatorsTest {

	private String [][] expectedDataSet = new String[][] {
		{"SWE_NY_GDP_MKTP_KD_ZG", "GDP growth Sweden"}
	};
	
	
	@Test
	public void getLinkToDatabaseCode() throws Exception {
		assertEquals("WWDI", new WorldBankWorldDevelopmentIndicators().link());
	}
	
	@Test
	public void getDataSetsFromDatabase() throws Exception {
		assertArrayEquals(expectedDataSet, new WorldBankWorldDevelopmentIndicators().dataSet());
	}
	
	@Test
	public void getDataSourceFromDatabase() throws Exception {
		Database db = new WorldBankWorldDevelopmentIndicators();
		assertNotNull(db.getSource("SWE_NY_GDP_MKTP_KD_ZG"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void throwsExceptionWhenDataSourceDoesNotExistInDataBase() throws Exception {
		new WorldBankWorldDevelopmentIndicators().getSource("NEJ");
	}

}
