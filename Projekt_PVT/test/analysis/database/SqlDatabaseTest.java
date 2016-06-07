package analysis.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

import analysis.Analysis;
import analysis.Comment;
import analysis.DatabaseWithSource;
import analysis.DateRange;
import analysis.Title;
import compare.DataSource;
import compare.Resolution;
import database.Database;

public class SqlDatabaseTest {

	private SqlTable table;
	private SqlDatabase sql;
	private Connection conn;
	private ResultSet rs = mock(ResultSet.class);

	@Before
	public void setUp() throws Exception {
		table = mock(SqlTable.class);
		when(table.name()).thenReturn("GENERICNAME");
		conn = mock(Connection.class);
		when(table.connectToDatabase()).thenReturn(conn);
		Statement st = mock(Statement.class);
		when(conn.createStatement()).thenReturn(st);
		when(st.executeQuery("SELECT * FROM \"" + table.name() + "\"")).thenReturn(rs);
		
		insertMockAnalysesByTitle("hej", "hallå");
		when(rs.next()).thenReturn(true, true, false);
		
		sql = new SqlDatabase(table);
	}

	@Test(expected = NullPointerException.class)
	public void constructorArgumentCannotBeNull() throws Exception {
		new SqlDatabase(null);
	}
	
	@Test
	public void getTitlesOfSavedAnalyses() throws Exception {
		assertEquals("number of analyses should be 2", 2, sql.getSavedTitles().size());
	}

	@Test
	public void canStoreValues() throws Exception {
		PreparedStatement ps = mock(PreparedStatement.class);
		when(conn.prepareStatement("INSERT INTO \"" + table.name() + "\" \nVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")).thenReturn(ps);
		when(rs.getString("TITLE")).thenReturn("hej", "hallå", "hejsan");
		when(rs.next()).thenReturn(true, true, true, false);
			
		SqlDatabase saveData = sql.saveData(createAnalysis(new Title("test")));
		assertEquals(2, sql.getSavedTitles().size());
		assertEquals(3, saveData.getSavedTitles().size());
	}
	
	@Test
	public void canRetrieveAnalyses() throws Exception {
		assertEquals(Analysis.class, sql.getSavedData(new Title("hej")).getClass());
	}
	
	@Test
	public void deletingValuesReturnsNewInstanceWithValueDeleted() throws Exception {
		when(conn.prepareStatement("DELETE FROM \"" + table.name() + "\"\nWHERE \"TITLE\" = ?")).thenReturn(mock(PreparedStatement.class));
		when(rs.getString("TITLE")).thenReturn("hallå");
		when(rs.next()).thenReturn(true, false);
		SqlDatabase deleted = sql.deleteData(new Title("hej"));
		assertEquals("old instance still has 2 values stored", 2, sql.getSavedTitles().size());
		assertEquals("new instance has one less value", 1, deleted.getSavedTitles().size());
		
		assertTrue(sql.getSavedTitles().contains(new Title("hej")));
		assertFalse(deleted.getSavedTitles().contains(new Title("hej")));
	}
	
	@Test
	public void canUpdateACommentOnASavedAnalysis() throws Exception {
		when(conn.prepareStatement("UPDATE \"" + table.name() + "\" \nSET \"COMMENT\" = ? " + " \nWHERE \"TITLE\" = ?")).thenReturn(mock(PreparedStatement.class));
		when(rs.next()).thenReturn(true, true, false);
		when(rs.getString("TITLE")).thenReturn("hej", "hallå");
		when(rs.getString("COMMENT")).thenReturn("new");
		Title savedAnalyisTitle = new Title("hej");
		SqlDatabase updated = sql.updateData(createAnalysisWithComment(savedAnalyisTitle, new Comment("new")));
		assertEquals("No comment", sql.getSavedData(savedAnalyisTitle).getComment().toString());
		assertNotEquals("No comment", updated.getSavedData(savedAnalyisTitle).getComment().toString());
	}
	
	private void insertMockAnalysesByTitle(String title, String... titles) throws SQLException {
		when(rs.getString("TITLE")).thenReturn(title, titles);
		when(rs.getString("DATABASE_1")).thenReturn("DATABASE");
		when(rs.getString("SOURCE_1")).thenReturn("SOURCE");
		when(rs.getString("DATABASE_2")).thenReturn("DATABASE");
		when(rs.getString("SOURCE_2")).thenReturn("SOURCE");
		when(rs.getString("RESOLUTION")).thenReturn("DAY");
		when(rs.getString("START_DATE")).thenReturn("2014-01-01");
		when(rs.getString("END_DATE")).thenReturn("2015-01-01");
		when(rs.getString("COMMENT")).thenReturn("No comment");
	}
	
	
	private Analysis createAnalysis(Title title) {
		return createAnalysisWithComment(title, new Comment("No comment"));
	}
	
	private Analysis createAnalysisWithComment(Title title, Comment comment) {
		DatabaseWithSource dbws = mock(DatabaseWithSource.class);
		Database db = mock(Database.class);
		when(dbws.getDatabase()).thenReturn(db);
		when(dbws.getSourceId()).thenReturn("SOURCE");
		when(db.link()).thenReturn("DATABASE");
		when(db.getSource("SOURCE")).thenReturn(mock(DataSource.class));
		return new Analysis(
				dbws, 
				dbws, 
				Resolution.DAY, 
				new DateRange("2015-01-10", "2015-02-10"), 
				title, 
				comment);
	}
}