package database.misc;

import static org.junit.Assert.*;

import org.junit.Test;

import database.Database;

public class MiscDatabaseTest {
	
	private MiscDatabase createDatabase() {
		return new MiscDatabase();
	}
	
	private final String[][] expectedDataset = {
			{"gold", "Gold prices (EUR)"},
			{"temperature", "Temperature"},
			{"spectators", "Spectators"},
			{"goals", "Antal mål per matchdag i fotbollsallsvenskan"},
	};
	
	@Test
	public void testLink() throws Exception {
		Database db = createDatabase();
		assertEquals("misc", db.link());
	}
	
	@Test
	public void getDataset() throws Exception {
		Database db = createDatabase();
		assertArrayEquals(expectedDataset, db.dataSet());
	}
	
	@Test
	public void getValidSources() throws Exception {
		MiscDatabase db = createDatabase();
		
		for (String[] strings : expectedDataset) 
			assertEquals(strings[1], db.getSource(strings[0]).getName());
		
	}
	
	@Test
	public void returnsNullWhenTryingToRetrieveDataSourceUsingWrongID() throws Exception {
		assertNull(createDatabase().getSource("INVALID"));
		assertNull(createDatabase().getSource(null));
	}
	
}
