package compare;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class ResolutionTest {

	@Test
	public void turnLocalDateIntoDayFormat() throws Exception {
		assertEquals("2016-05-04", Resolution.DAY.getLocalDate(LocalDate.parse("2016-05-04")));
	}
	
	@Test
	public void turnLocalDateIntoWeekFormat() throws Exception {
		assertEquals("2015-W3", Resolution.WEEK.getLocalDate(LocalDate.parse("2015-01-12")));
	}
	
	@Test
	public void turnLocalDateIntoMonthFormat() throws Exception {
		assertEquals("1999-12", Resolution.MONTH.getLocalDate(LocalDate.parse("1999-12-30")));
	}
	
	@Test
	public void turnLocalDateIntoQuarterFormat() throws Exception {
		assertEquals("2007-Q1", Resolution.QUARTER.getLocalDate(LocalDate.parse("2007-02-28")));
	}
	
	@Test
	public void turnLocalDateIntoYearFormat() throws Exception {
		assertEquals("1995", Resolution.YEAR.getLocalDate(LocalDate.parse("1995-03-28")));
	}
	
}
