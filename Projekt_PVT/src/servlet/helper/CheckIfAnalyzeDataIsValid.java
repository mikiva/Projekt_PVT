package servlet.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import compare.Resolution;
import database.Database;
import database.DatabaseFactory;
import database.NullDatabase;

public class CheckIfAnalyzeDataIsValid {
	private DatabaseFactory df;

	public boolean validDate(String date) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			@SuppressWarnings("unused")
			LocalDate localDate = LocalDate.parse(date, formatter);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public boolean validResulution(String resulution) {
		return Arrays.stream(Resolution.values()).anyMatch(e -> e.name().equals(resulution.toUpperCase()));
	}

	public boolean databaseExist(String database) {
		boolean exist = true;

		df = new DatabaseFactory();
		@SuppressWarnings("static-access")
		Database db = df.get(database);
		NullDatabase nullDatabase = new NullDatabase();

		if (db.getClass() == nullDatabase.getClass())
			exist = false;

		return exist;
	}

	public boolean dataSetExist(String database, String datasets) {
		df = new DatabaseFactory();

		@SuppressWarnings("static-access")
		Database db = df.get(database);
		String[][] ds = db.dataSet();

		for (String[] array : ds) {
			String dataS = array[0];
			if (dataS.equals(datasets))
				return true;
		}

		return false;
	}
	
	public boolean isAlphaNumeric(String s){
	    String pattern= "^[a-zA-Z0-9]*$";
	    return pattern.matches(s); 
	}
}
