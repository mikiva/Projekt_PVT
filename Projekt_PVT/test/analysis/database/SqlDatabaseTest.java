package analysis.database;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import analysis.Analysis;
import analysis.Title;

public class SqlDatabaseTest {

	private SqlTable table;
	private SqlDatabase sql;

	@Before
	public void setUp() throws Exception {
		table = mock(SqlTable.class);
		sql = new SqlDatabase(table);
	}

	@Test(expected = NullPointerException.class)
	public void constructorArgumentCannotBeNull() throws Exception {
		new SqlDatabase(null);
	}
	
	@Test
	@Ignore
	public void getTitlesOfSavedAnalyses() throws Exception {
		Connection mockedConnection = mock(Connection.class);
		PreparedStatement mockedStatement = mock(PreparedStatement.class);
		when(mockedConnection.prepareStatement("SELECT * FROM ?")).thenReturn(mockedStatement);
		when(table.connectToDatabase()).thenReturn(mockedConnection);
		when(table.name()).thenReturn("GENERICNAME");
		when(mockedStatement.executeQuery()).thenReturn(mock(ResultSet.class));
		assertNotNull(sql.getSavedTitles());
	}
	
	@Test
	@Ignore
	public void canStoreAndRetrieveValues() throws Exception {
		Analysis a = mock(Analysis.class);
		Title title = new Title("Titel");
		when(a.getTitle()).thenReturn(title);
		sql.saveData(a);
		assertEquals("get saved data", a, sql.getSavedData(title));
		
		//TODO: mocka på något sätt
	}
}
