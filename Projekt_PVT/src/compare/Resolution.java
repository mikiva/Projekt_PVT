package compare;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public enum Resolution {
	
	YEAR("yyyy"),

	MONTH("yyyy-MM"),

	QUARTER("yyyy-'Q'q"),

	WEEK("YYYY-'W'w"),

	DAY("yyyy-MM-dd");

	final DateTimeFormatter dtf;

	/**
	 * 
	 * @param date
	 *            LocalDate that is to be formated.
	 * @return a string in a format defined in the constructor.
	 */
	public String getLocalDate(LocalDate date) {
		return date.format(dtf);
	}

	private Resolution(String pattern) {

		dtf = DateTimeFormatter.ofPattern(pattern);
	}
}