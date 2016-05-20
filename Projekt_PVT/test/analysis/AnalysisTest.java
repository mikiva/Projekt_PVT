package analysis;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import compare.DataSource;
import compare.Resolution;
import database.Database;

public class AnalysisTest {

	private Analysis analysis;
	
	private DatabaseWithSource dbAndSource1;
	private DatabaseWithSource dbAndSource2;
	private Resolution resolution;
	private DateRange dates;
	private Title title;

	@Before
	public void setUp() {
		Database mockDB1 = mock(Database.class);
		Database mockDB2 = mock(Database.class);
		when(mockDB1.getSource("id_1")).thenReturn(mock(DataSource.class));
		when(mockDB2.getSource("id_2")).thenReturn(mock(DataSource.class));
		dbAndSource1 = new DatabaseWithSource(mockDB1, "id_1");
		dbAndSource2 = new DatabaseWithSource(mockDB2, "id_2");
		resolution = Resolution.DAY;
		dates = new DateRange("2015-01-01", "2016-01-01");
		title = new Title("Title");
		analysis =  new Analysis(dbAndSource1, dbAndSource2, resolution, dates, title);
	}
	
	@Test
	public void getMethods() throws Exception {
		assertEquals("first database and source", dbAndSource1, analysis.getFirstDatabaseWithSource());
		assertEquals("second database and source", dbAndSource2, analysis.getSecondDatabaseAndSource());
		assertEquals("resolution", resolution, analysis.getResolution());
		assertEquals("the two dates", dates, analysis.getDateRange());
		assertEquals("title", title, analysis.getTitle());
	}
	
	@Test(expected = NullPointerException.class)
	public void throwExceptionIfAnyArgumentIsNull() throws Exception {
		new Analysis(null, null, null, null, null);
	}

}
