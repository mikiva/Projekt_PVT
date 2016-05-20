package analysis;

import compare.DataSource;
import database.Database;

public class DatabaseWithSource {
	
	private final String sourceId;
	private final Database database;
	private final DataSource dataSource;

	public DatabaseWithSource(Database database, String sourceId) {
		legalCheck(database, sourceId);
		this.sourceId = sourceId;
		this.database = database;
		this.dataSource = database.getSource(sourceId);
	}

	private void legalCheck(Database database, String sourceId) {
		if (database == null)
			throw new NullDatabaseException();
		if (database.getSource(sourceId) == null)
			throw new DataSourceException("ID returned null value!");
	}
	
	public DataSource getSource() {
		return dataSource;
	}

	public Database getDatabase() {
		return database;
	}
	
	public String getSourceId() {
		return sourceId;
	}
	
	class NullDatabaseException extends RuntimeException {
		private static final long serialVersionUID = 3217394004683768549L;
		
		public NullDatabaseException() {
			super();
		}
	}

	class DataSourceException extends RuntimeException {
		private static final long serialVersionUID = 4510766211481132340L;

		public DataSourceException() {
			super();
		}

		public DataSourceException(String msg) {
			super(msg);
		}
	}



}