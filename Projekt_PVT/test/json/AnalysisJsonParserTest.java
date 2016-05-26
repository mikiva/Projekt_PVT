package json;

import static org.junit.Assert.*;

import org.junit.Test;

import analysis.Analysis;
import analysis.DatabaseWithSource;
import analysis.DateRange;
import analysis.Title;
import compare.DataSource;
import compare.Resolution;
import database.Database;

import static org.mockito.Mockito.*;
public class AnalysisJsonParserTest {
	
	private final static String EXPECTED = 
			"{"
			+ "\"title\":" + "\"Titel\","
			+ "\"database1\":" + "\"database_link\","
			+ "\"datasource1\":" + "\"dbId\","
			+ "\"database2\":" + "\"database_link\","
			+ "\"datasource2\":" + "\"dbId\","
			+ "\"resolution\":" + "\"DAY\","
			+ "\"startDate\":" + "\"2014-01-01\","
			+ "\"endDate\":" + "\"2015-01-01\""
			+ "}";

	@Test
	public void analysisToJson() {
		DatabaseWithSource dbS = mockDatabaseAndItsSource();
		Resolution resolution = Resolution.DAY;
		DateRange dates = new DateRange("2014-01-01", "2015-01-01");
		Title title = new Title("Titel");
		
		Analysis a = new Analysis(dbS, dbS, resolution, dates, title);
		
		AnalysisJsonParser parser = new AnalysisJsonParser(a);
		System.out.println(new JsonFormatter().format(parser.toJsonString()));
		assertEquals(EXPECTED, parser.toJsonString());
	}

	private DatabaseWithSource mockDatabaseAndItsSource() {
		Database dbMock = mock(Database.class);
		when(dbMock.link()).thenReturn("database_link");
		DatabaseWithSource dbS = mock(DatabaseWithSource.class);
		when(dbS.getSourceId()).thenReturn("dbId");
		when(dbS.getDatabase()).thenReturn(dbMock);
		return dbS;
	}

}
