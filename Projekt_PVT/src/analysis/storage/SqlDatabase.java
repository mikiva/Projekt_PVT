package analysis.storage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import analysis.Analysis;
import analysis.AnalysisTitle;
import analysis.DatabaseWithSource;
import analysis.DateRange;
import compare.Resolution;
import database.Database;
import database.DatabaseFactory;
import database.misc.MiscDatabase;

public class SqlDatabase {

	private final SqlTable table;

	public SqlDatabase(SqlTable table) {
		if (table == null)
			throw new NullPointerException();
		this.table = table;
	}


	public boolean saveData(Analysis a) {
		String query = "INSERT INTO \"" + table.name() + "\"\nVALUES ('" + a.getTitle() + "','"
				+ a.getFirstDatabaseWithSource().getDatabase().link() + "','"
				+ a.getFirstDatabaseWithSource().getSourceId() + "','"
				+ a.getSecondDatabaseAndSource().getDatabase().link() + "','"
				+ a.getSecondDatabaseAndSource().getSourceId() + "','" + a.getResolution() + "','"
				+ a.getDateRange().getStartDate() + "','" + a.getDateRange().getEndDate() + "');";

		System.out.println(query);
		try (Connection conn = table.connectToDatabase()) {
			conn.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public Analysis getSavedData(AnalysisTitle title) {
		Analysis analysis = null;
		String query = "SELECT * FROM \"" + table.name() + "\" WHERE \"TITLE\"='" + title + "'";
		System.out.println(query);
		try (Connection conn = table.connectToDatabase()) {
			ResultSet rs = conn.createStatement().executeQuery(query);

			while (rs.next()) {
				AnalysisTitle aTitle = new AnalysisTitle(rs.getString("TITLE"));
				Database db1 = DatabaseFactory.get(rs.getString("DATABASE_1"));
				DatabaseWithSource dbWithSource1 = new DatabaseWithSource(db1, rs.getString("SOURCE_1"));
				Database db2 = DatabaseFactory.get(rs.getString("DATABASE_2"));
				DatabaseWithSource dbWithSource2 = new DatabaseWithSource(db2, rs.getString("SOURCE_2"));
				Resolution resolution = Resolution.valueOf(rs.getString("RESOLUTION"));
				DateRange dates = new DateRange(rs.getString("START_DATE"), rs.getString("END_DATE"));

				analysis = new Analysis(dbWithSource1, dbWithSource2, resolution, dates, aTitle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	public static void main(String[] args) {
		Analysis a = new Analysis(new DatabaseWithSource(new MiscDatabase(), "spectators"),
				new DatabaseWithSource(new MiscDatabase(), "temperature"), Resolution.DAY,
				new DateRange("2015-02-14", "2016-03-18"), new AnalysisTitle("Java"));
		SqlTable table = BulleTable.getInstance();
		SqlDatabase db = new SqlDatabase(table);

		System.out.println(db.getSavedData(new AnalysisTitle("Java")));
	}

}
