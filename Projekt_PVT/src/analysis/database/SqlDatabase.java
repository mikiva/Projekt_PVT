package analysis.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

import analysis.Analysis;
import analysis.Comment;
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

	public void saveData(Analysis analysis) {
		if (getSavedTitles().contains(analysis.getTitle()))
			throw new RuntimeException("Title " + analysis.getTitle() + " already exists!");

		try (Connection conn = table.connectToDatabase()) {
			String query = "INSERT INTO \"" + table.name() + "\"\nVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
			PreparedStatement statement = conn.prepareStatement(query);

			statement.setString(1, analysis.getTitle().toString());
			statement.setString(2, analysis.getFirstDatabaseWithSource().getDatabase().link());
			statement.setString(3, analysis.getFirstDatabaseWithSource().getSourceId());
			statement.setString(4, analysis.getSecondDatabaseAndSource().getDatabase().link());
			statement.setString(5, analysis.getSecondDatabaseAndSource().getSourceId());
			statement.setString(6, analysis.getResolution().toString());
			statement.setString(7, analysis.getDateRange().getStartDate().toString());
			statement.setString(8, analysis.getDateRange().getEndDate().toString());
			statement.setString(9, analysis.getComment().toString());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new TableException(e);
		}
	}

	public void updateData(Analysis analysis){

		try(Connection conn = table.connectToDatabase()){

			String query = "UPDATE \"" + table.name() + "\" \nSET COMMENT = ? "
					+  " \nWHERE TITLE = ?";

			PreparedStatement statement = conn.prepareStatement(query);
			
			statement.setString(1,  analysis.getComment().toString());
			statement.setString(2,  analysis.getTitle().toString());
			
			statement.executeUpdate();

		}
		catch(SQLException e){
			throw new TableException(e);
		}
	}

	public Analysis getSavedData(Title title) {
		Analysis analysis = null;
		String query = "SELECT * FROM \"" + table.name() + "\" WHERE \"TITLE\"='" + title + "'";
		System.out.println(query);
		try (Connection conn = table.connectToDatabase()) {
			ResultSet rs = conn.createStatement().executeQuery(query);
			if (rs.next())
				analysis = createAnalysis(rs);

		} catch (SQLException e) {
			throw new TableException(e);
		}
		return analysis;
	}

	private Analysis createAnalysis(ResultSet rs) throws SQLException {
		Analysis analysis;
		Title title = new Title(rs.getString("TITLE"));
		Database db1 = DatabaseFactory.get(rs.getString("DATABASE_1"));
		DatabaseWithSource dbWithSource1 = new DatabaseWithSource(db1, rs.getString("SOURCE_1"));
		Database db2 = DatabaseFactory.get(rs.getString("DATABASE_2"));
		DatabaseWithSource dbWithSource2 = new DatabaseWithSource(db2, rs.getString("SOURCE_2"));
		Resolution resolution = Resolution.valueOf(rs.getString("RESOLUTION"));
		DateRange dates = new DateRange(rs.getString("START_DATE"), rs.getString("END_DATE"));
		Comment comment = new Comment(rs.getString("COMMENT"));

		analysis = new Analysis(dbWithSource1, dbWithSource2, resolution, dates, title, comment);
		return analysis;
	}

	public Set<Title> getSavedTitles() {
		Set<Title> titles = new TreeSet<>();

		try (Connection conn = table.connectToDatabase()) {
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM \"" + table.name() + "\"");
			while (rs.next())
				titles.add(new Title(rs.getString("TITLE")));
		} catch (SQLException e) {
			throw new TableException("Error connecting to database");
		}
		return titles;
	}
}
