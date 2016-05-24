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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DateRange other = (DateRange) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}
	
	
}
