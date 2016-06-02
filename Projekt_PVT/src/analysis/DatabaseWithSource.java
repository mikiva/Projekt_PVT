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
		if (sourceId == null)
			throw new NullDataSourceException();
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
	
	class NullDatabaseException extends NullPointerException {
		private static final long serialVersionUID = 3217394004683768549L;
		
		public NullDatabaseException() {
			super();
		}
	}

	class NullDataSourceException extends NullPointerException {
		private static final long serialVersionUID = 4510766211481132340L;

		public NullDataSourceException() {
			super();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataSource == null) ? 0 : dataSource.hashCode());
		result = prime * result + ((database == null) ? 0 : database.hashCode());
		result = prime * result + ((sourceId == null) ? 0 : sourceId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatabaseWithSource other = (DatabaseWithSource) obj;
		if (dataSource == null) {
			if (other.dataSource != null)
				return false;
		} else if (!dataSource.equals(other.dataSource))
			return false;
		if (database == null) {
			if (other.database != null)
				return false;
		} else if (!database.equals(other.database))
			return false;
		if (sourceId == null) {
			if (other.sourceId != null)
				return false;
		} else if (!sourceId.equals(other.sourceId))
			return false;
		return true;
	}
	
}
