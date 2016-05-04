package database.quandl;

import compare.DataSource;
import database.Database;

public class WorldBankWorldDevelopmentIndicators implements Database {
	
	private final String databaseCode;
	private final static String ID = "SWE_NY_GDP_MKTP_KD_ZG";
	private final static SourceProperties PROP = new SourceProperties("GDP growth Sweden", "Annual %");

	public WorldBankWorldDevelopmentIndicators() {
		this.databaseCode = "WWDI";
	}

	@Override
	public String link() {
		return databaseCode;
	}

	@Override
	public String[][] dataSet() {
		return new String[][] { { ID, PROP.name() } };
	}

	@Override
	public DataSource getSource(String id) {
		if (!id.equals(ID))
			throw new IllegalArgumentException("Database doesn't have source: " + id);

		return QuandlSource.createFrom(this.databaseCode, id, PROP);
	}

	@Override
	public String toString() {
		return "WorldBankWorldDevelopmentIndicators";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((databaseCode == null) ? 0 : databaseCode.hashCode());
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
		WorldBankWorldDevelopmentIndicators other = (WorldBankWorldDevelopmentIndicators) obj;
		if (databaseCode == null) {
			if (other.databaseCode != null)
				return false;
		} else if (!databaseCode.equals(other.databaseCode))
			return false;
		return true;
	}

}
