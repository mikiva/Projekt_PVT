package analysis;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.Test;

import analysis.DateRange.NullDateException;

public class DateRangeTest {

	@Test(expected = DateTimeParseException.class)
	public void argumentsMustBeParseableToLocalDate() {
		new DateRange("Invalid", "arguments");
	}
	
	@Test(expected = NullDateException.class)
	public void argumentsMustNotBeNull() throws Exception {
		new DateRange(null, null);
	}
	
	@Test
	public void getDates() throws Exception {
		LocalDate start = LocalDate.MIN;
		LocalDate end = LocalDate.MAX;
		DateRange dates = new DateRange(start.toString(), end.toString());
		assertEquals(start, dates.getStartDate());
		assertEquals(end, dates.getEndDate());
	}

}
