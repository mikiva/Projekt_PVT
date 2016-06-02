package analysis.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AnalysisTable implements SqlTable {

	private final static String URL = "jdbc:postgresql://localhost:5433/Bulle?characterEncoding=utf8";
	private final static String USERNAME = "webbulle";
	private final static String PASSWORD = "bulle4bulle";

	private static SqlTable singel = null;

	private AnalysisTable() {}

	public static SqlTable getInstance() {
		if (singel == null)
			singel = new AnalysisTable();
		return singel;
	}

	@Override
	public Connection connectToDatabase() {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			throw new TableException(e);
		} 
		return conn;
	}

	@Override
	public String name() {
		return "Analysis";
	}
}
