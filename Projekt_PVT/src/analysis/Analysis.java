package analysis;

import java.util.Arrays;

import compare.Resolution;

public class Analysis {

	private final DatabaseWithSource dbAndSource1;
	private final DatabaseWithSource dbAndSource2;
	private final Resolution resolution;
	private final DateRange dates;
	private final Title title;
	private final Comment comment;

	public Analysis(DatabaseWithSource dbAndSource1, DatabaseWithSource dbAndSource2, Resolution resolution,
			DateRange dates, Title title, Comment comment) {
		checkForNull(dbAndSource1, dbAndSource2, resolution, dates, title);
		this.dbAndSource1 = dbAndSource1;
		this.dbAndSource2 = dbAndSource2;
		this.resolution = resolution;
		this.dates = dates;
		this.title = title;
		this.comment = comment;
	}

	private void checkForNull(Object... objects) {
		if (Arrays.stream(objects).anyMatch(o -> o == null)) {
			throw new NullPointerException("Arguments cannot be null, values are -\n"
					+ "Database and source 1: " + dbAndSource1 + 
					",\nDatabase and source 2: " + dbAndSource2 + 
					",\nResolution: " + resolution + 
					",\nDate range: " + dates + 
					",\nTitle: " + title);
		}
	}

	public DatabaseWithSource getFirstDatabaseWithSource() {
		return dbAndSource1;
	}

	public DatabaseWithSource getSecondDatabaseAndSource() {
		return dbAndSource2;
	}

	public Resolution getResolution() {
		return resolution;
	}

	public DateRange getDateRange() {
		return dates;
	}

	public Title getTitle() {
		return title;
	}

	public Comment getComment() {
		return comment;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dates == null) ? 0 : dates.hashCode());
		result = prime * result + ((dbAndSource1 == null) ? 0 : dbAndSource1.hashCode());
		result = prime * result + ((dbAndSource2 == null) ? 0 : dbAndSource2.hashCode());
		result = prime * result + ((resolution == null) ? 0 : resolution.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Analysis other = (Analysis) obj;
		if (dates == null) {
			if (other.dates != null)
				return false;
		} else if (!dates.equals(other.dates))
			return false;
		if (dbAndSource1 == null) {
			if (other.dbAndSource1 != null)
				return false;
		} else if (!dbAndSource1.equals(other.dbAndSource1))
			return false;
		if (dbAndSource2 == null) {
			if (other.dbAndSource2 != null)
				return false;
		} else if (!dbAndSource2.equals(other.dbAndSource2))
			return false;
		if (resolution != other.resolution)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	
	

}
