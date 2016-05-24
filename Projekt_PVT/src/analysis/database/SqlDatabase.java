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

public class SqlDatabase {

	private final SqlTable table;

	public SqlDatabase(SqlTable table) {
		if (table == null)
			throw new NullPointerException();
		this.table = table;
	}

	public void saveData(Analysis a) {
		
		if(getSavedTitles().contains(a.getTitle())) {
			throw new RuntimeException("Title already exists!");
		}
		
		String query = "INSERT INTO \"" + table.name() + "\"\nVALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		System.out.println(query);
		try (Connection conn = table.connectToDatabase()) {
			PreparedStatement statement = conn.prepareStatement(query);

			statement.setString(1, a.getTitle().toString());
			statement.setString(2, a.getFirstDatabaseWithSource().getDatabase().link());
			statement.setString(3, a.getFirstDatabaseWithSource().getSourceId());
			statement.setString(4, a.getSecondDatabaseAndSource().getDatabase().link());
			statement.setString(5, a.getSecondDatabaseAndSource().getSourceId());
			statement.setString(6, a.getResolution().toString());
			statement.setString(7, a.getDateRange().getStartDate().toString());
			statement.setString(8, a.getDateRange().getEndDate().toString());

			statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Analysis getSavedData(Title title) {
		Analysis analysis = null;
		String query = "SELECT * FROM \"" + table.name() + "\" WHERE \"TITLE\"='" + title + "'";
		System.out.println(query);
		try (Connection conn = table.connectToDatabase()) {
			ResultSet rs = conn.createStatement().executeQuery(query);

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
		}
		return analysis;
	}

	public List<Title> getSavedTitles() {
		List<Title> titles = new ArrayList<>();
		
		try (Connection conn = table.connectToDatabase()) {
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM \"" + table.name() + "\"");
			while (rs.next()) {
				titles.add(new Title(rs.getString("TITLE")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			throw new RuntimeException("Something happened");
		}
		return titles;
	}
}
