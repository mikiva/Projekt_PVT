package analysis;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import analysis.DatabaseWithSource.NullDataSourceException;
import analysis.DatabaseWithSource.NullDatabaseException;
import compare.DataSource;
import database.Database;

public class DatabaseWithSourceTest {
	
	private static final String ID = "id";
	private DatabaseWithSource dbAndSource;
	private Database mockDB;
	private DataSource mockSource;

	@Before
	public void setUp() {
		mockDB = mock(Database.class);
		mockSource = mock(DataSource.class);
		when(mockDB.getSource(ID)).thenReturn(mockSource);
		dbAndSource = new DatabaseWithSource(mockDB, ID);
	}

	@Test(expected = NullDatabaseException.class)
	public void databaseShouldNotEverBeNull() {
		new DatabaseWithSource(null, ID);
	}

	@Test(expected = NullDataSourceException.class)
	public void dataSourceShouldNotEverBeNull() throws Exception {
		new DatabaseWithSource(mock(Database.class), null);
	}

	@Test
	public void idMustReturnCorrectDataSource() throws Exception {
		assertEquals(mockSource, dbAndSource.getSource());
	}
	
	@Test
	public void getDatabase() throws Exception {
		assertEquals(mockDB, dbAndSource.getDatabase());
	}
	
	@Test
	public void getSourceId() throws Exception {
		assertEquals(ID, dbAndSource.getSourceId());
	}
	
}
