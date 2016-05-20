package analysis;

import java.time.LocalDate;

public final class DateRange {
	
	private final LocalDate start;
	private final LocalDate end;

	public DateRange(final String startDate, final String endDate) {
		checkForNull(startDate, endDate);
		this.start = LocalDate.parse(startDate);
		this.end = LocalDate.parse(endDate);
	}

	public void checkForNull(String startDate, String endDate) {
		if(startDate == null || endDate == null) 
			throw new NullDateException("Dates cannot be null! Actual values are - start: " + startDate + ", end: " + endDate);
	}
	
	public LocalDate getStartDate() {
		return start;
	}
	
	public LocalDate getEndDate() {
		return end;
	}
	
	class NullDateException extends NullPointerException {
		private static final long serialVersionUID = 1L;

		public NullDateException(String msg) {
			super(msg);
		}
	}
}
