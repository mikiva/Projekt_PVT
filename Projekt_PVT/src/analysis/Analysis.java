package analysis;

import java.util.Arrays;
import java.util.Objects;

import compare.Resolution;
import database.Database;

public class Analysis {

	private final DatabaseWithSource dbAndSource1;
	private final DatabaseWithSource dbAndSource2;
	private final Resolution resolution;
	private final DateRange dates;
	private final Title title;

	public Analysis(DatabaseWithSource dbAndSource1, DatabaseWithSource dbAndSource2, Resolution resolution,
			DateRange dates, Title title) {
		checkForNull(dbAndSource1, dbAndSource2, resolution, dates, title);
		this.dbAndSource1 = dbAndSource1;
		this.dbAndSource2 = dbAndSource2;
		this.resolution = resolution;
		this.dates = dates;
		this.title = title;
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
	
	public static void main(String[] args) {
		new Analysis(null, null, null, null, null);
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

}
