package analysis.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import analysis.Analysis;
import analysis.AnalysisTitle;

public class SqlDatabaseTest {

	private SqlTable mock;
	private SqlDatabase sql;

	@Before
	public void setUp() throws Exception {
		mock = mock(SqlTable.class);
		sql = new SqlDatabase(mock);
		Connection mockedConnection = mock(Connection.class);
		Statement mockedStatement = mock(Statement.class);
		when(mockedConnection.createStatement()).thenReturn(mockedStatement);
		when(mock.connectToDatabase()).thenReturn(mockedConnection);
	}

	@Test(expected = NullPointerException.class)
	public void constructorArgumentCannotBeNull() throws Exception {
		new SqlDatabase(null);
	}
	
	@Test
	@Ignore
	public void canStoreAndRetrieveValues() throws Exception {
		Analysis a = mock(Analysis.class);
		AnalysisTitle title = new AnalysisTitle("Titel");
		when(a.getTitle()).thenReturn(title);
		assertTrue("save data", sql.saveData(a));
		assertEquals("get saved data", a, sql.getSavedData(title));
		
		//TODO: mocka på något sätt
	}
}
