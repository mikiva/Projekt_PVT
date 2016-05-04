package database.quandl;

import java.util.HashMap;
import java.util.Map;

import compare.DataSource;
import database.Database;

public class IMFCrossCountryMacroeconomicStatistics implements Database {
	private final String databaseCode;
	private final static Map<String, SourceProperties> DATASET = new HashMap<>();

	public IMFCrossCountryMacroeconomicStatistics() {
		this.databaseCode = "ODA";
	}

	@Override
	public String link() {
		return databaseCode;
	}

	@Override
	public String[][] dataSet() {
		return DATASET.entrySet().stream().map(entry -> new String[] { entry.getKey(), entry.getValue().name() })
				.toArray(String[][]::new);
	}

	@Override
	public DataSource getSource(String id) {
		if (!DATASET.containsKey(id))
			throw new IllegalArgumentException("Database doesn't have source: " + id);

		return QuandlSource.createFrom(this.databaseCode, id, DATASET.get(id));
	}

	static {
		DATASET.put("PBANSOP_USD", new SourceProperties("Bananas", "US dollar per metric ton"));
		DATASET.put("SWE_LE", new SourceProperties("Sweden-Employment", "Millions"));
		DATASET.put("SWE_LP", new SourceProperties("Sweden Population", "Millions"));
	}
	
	@Override
	public String toString() {
		return "IMFCrossCountryMacroeconomicStatistics";
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
		IMFCrossCountryMacroeconomicStatistics other = (IMFCrossCountryMacroeconomicStatistics) obj;
		if (databaseCode == null) {
			if (other.databaseCode != null)
				return false;
		} else if (!databaseCode.equals(other.databaseCode))
			return false;
		return true;
	}
}
