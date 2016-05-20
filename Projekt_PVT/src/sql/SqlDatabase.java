package sql;

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

public class SqlDatabase implements AnalysisDatabase {

	private final SqlTable table;

	public SqlDatabase(SqlTable table) {
		if (table == null)
			throw new NullPointerException();
		this.table = table;
	}

	@Override
	public boolean saveData(Analysis a) {
		String query = "INSERT INTO " + table.name() + 
				"\nVALUES ('" + a.getTitle() + "','" + a.getFirstDatabaseWithSource().getDatabase().link() + "','" +
				a.getFirstDatabaseWithSource().getSourceId() + "','" + 
				a.getSecondDatabaseAndSource().getDatabase().link() + "','" + 
				a.getSecondDatabaseAndSource().getSourceId() + "','" +
				a.getResolution() + "','" +
				a.getDateRange().getStartDate() + "','" +
				a.getDateRange().getEndDate() + "');";
		
		System.out.println(query);
		try (Connection conn = table.connectToDatabase()) {
			conn.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Analysis getSavedData(AnalysisTitle title) {
		Analysis analysis = null;
		try(Connection conn = table.connectToDatabase()) {
			ResultSet rs = conn.createStatement().executeQuery("sql för att hämta en analys");
			while(rs.next()) {
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

}
