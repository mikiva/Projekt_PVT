package database.quandl;

import java.util.Map;

import compare.DataSource;
import database.Database;

class QuandlDatabase implements Database {
	
	protected final String databaseCode;
	protected final Map<String, SourceProperties> dataset;
	
	protected QuandlDatabase(String databaseCode, Map<String, SourceProperties> dataset) {
		this.databaseCode = databaseCode;
		this.dataset = dataset;
	}

	@Override
	public String link() {
		return databaseCode;
	}

	@Override
	public String[][] dataSet() {
		return dataset.entrySet().stream().map(entry -> new String[] { entry.getKey(), entry.getValue().name() })
				.toArray(String[][]::new);
	}

	@Override
	public DataSource getSource(String id) {
		if (!dataset.containsKey(id))
			throw new IllegalArgumentException("Database doesn't have source: " + id);

		return QuandlSource.createFrom(this.databaseCode, id, dataset.get(id));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((databaseCode == null) ? 0 : databaseCode.hashCode());
		result = prime * result + ((dataset == null) ? 0 : dataset.hashCode());
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
		QuandlDatabase other = (QuandlDatabase) obj;
		if (databaseCode == null) {
			if (other.databaseCode != null)
				return false;
		} else if (!databaseCode.equals(other.databaseCode))
			return false;
		if (dataset == null) {
			if (other.dataset != null)
				return false;
		} else if (!dataset.equals(other.dataset))
			return false;
		return true;
	}
	
}