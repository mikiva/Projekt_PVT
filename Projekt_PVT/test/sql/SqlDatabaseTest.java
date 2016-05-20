package sql;

import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import analysis.Analysis;
import analysis.AnalysisTitle;
import analysis.DatabaseWithSource;
import analysis.DateRange;
import compare.Resolution;
import database.misc.MiscDatabase;

public class SqlDatabaseTest {

	private SqlTable mock;
	private AnalysisDatabase sql;

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
	public void canStoreAndRetrieveValues() throws Exception {
		Analysis a = mock(Analysis.class);
		AnalysisTitle title = new AnalysisTitle("Titel");
		when(a.getTitle()).thenReturn(title);
		assertTrue("save data", sql.saveData(a));
		assertEquals("get saved data", a, sql.getSavedData(title));
	}
	
	@Test
	public void print() throws Exception {
		when(mock.name()).thenReturn("TABLE_NAME");
		Analysis a = new Analysis(new DatabaseWithSource(new MiscDatabase(), "goals"), new DatabaseWithSource(new MiscDatabase(), "gold"),
				Resolution.DAY, new DateRange("2015-02-14", "2016-03-12"), new AnalysisTitle("Title"));
		sql.saveData(a);
	}
	
}
