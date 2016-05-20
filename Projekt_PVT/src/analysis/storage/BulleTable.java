package analysis.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import analysis.Analysis;

public class BulleTable implements SqlTable {

	private final static String URL = "jdbc:postgresql://localhost:5433/Bulle";
	private final static String USERNAME = "webbulle";
	private final static String PASSWORD = "bulle4bulle";
	//private final static String USERNAME = "ndi14psd";
	//private final static String PASSWORD = "mitt lösenord";

	private static SqlTable singel = null;

	private BulleTable() {
	}

	public static SqlTable getInstance() {
		if (singel == null)
			singel = new BulleTable();
		return singel;
	}

	@Override
	public Connection connectToDatabase() {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	@Override
	public String name() {
		return "Analysis";
	}
}
