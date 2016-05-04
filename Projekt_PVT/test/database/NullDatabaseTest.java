package database;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NullDatabaseTest {

	private NullDatabase makeNewNullDatabase() {
		return new NullDatabase();
	}
	
	@Test
	public void onlyReturnsNullWhenAskedForSources() throws Exception {
		assertNull(makeNewNullDatabase().getSource("test"));
		assertNull(makeNewNullDatabase().getSource(null));
	}

	@Test
	public void returnsEmptyDataset() throws Exception {
		assertTrue(makeNewNullDatabase().dataSet().length == 0);
	}
	
	@Test
	public void linkReturnsEmptyString() throws Exception {
		assertTrue(makeNewNullDatabase().link().isEmpty());
	}

}
