package analysis.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import analysis.Analysis;
import analysis.DatabaseWithSource;
import analysis.DateRange;
import analysis.Title;
import compare.Resolution;
import database.Database;
import database.DatabaseFactory;
import database.misc.MiscDatabase;

public class SqlDatabase {

	private final SqlTable table;
	private final static List<Analysis> SAVED_LIST = new ArrayList<>();

	public SqlDatabase(SqlTable table) {
		if (table == null)
			throw new NullPointerException();
		this.table = table;
	}


	public void saveData(Analysis a) {
		String query = "INSERT INTO ? \nVALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		System.out.println(query);
		try (Connection conn = table.connectToDatabase()) {
			PreparedStatement statement = conn.prepareStatement(query);
			
			statement.setString(1, table.name());
			statement.setString(2, a.getTitle().toString());
			statement.setString(3, a.getFirstDatabaseWithSource().getDatabase().link());
			statement.setString(4, a.getFirstDatabaseWithSource().getSourceId());
			statement.setString(5, a.getSecondDatabaseAndSource().getDatabase().link());
			statement.setString(6, a.getSecondDatabaseAndSource().getSourceId());
			statement.setString(7, a.getResolution().toString());
			statement.setString(8, a.getDateRange().getStartDate().toString());
			statement.setString(9, a.getDateRange().getEndDate().toString());
			
			statement.executeQuery();
			SqlDatabase.SAVED_LIST.add(a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public Analysis getSavedData(Title title) {
		Analysis analysis = null;
		String query = "SELECT * FROM ? WHERE \"TITLE\"=?";
		System.out.println(query);
		try (Connection conn = table.connectToDatabase()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, table.name());
			statement.setString(2, title.toString());
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				Title aTitle = new Title(rs.getString("TITLE"));
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
			System.out.println(e);
		}
		return analysis;
	}

	public static void main(String[] args) {
		Analysis a = new Analysis(new DatabaseWithSource(new MiscDatabase(), "spectators"),
				new DatabaseWithSource(new MiscDatabase(), "temperature"), Resolution.DAY,
				new DateRange("2015-02-14", "2016-03-18"), new Title("PreparedStatement"));
		SqlTable table = AnalysisTable.getInstance();
		SqlDatabase db = new SqlDatabase(table);

		db.saveData(a);
		System.out.println("Hämtade analys: " + db.getSavedData(new Title("PreparedStatement")).getTitle());
	}

}
