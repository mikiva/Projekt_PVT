package database.misc;

import java.util.HashMap;
import java.util.Map;

import compare.DataSource;
import database.Database;

public class MiscDatabase implements Database {

	private final String link;
	private static final Map<String, DataSource> data_sources = new HashMap<>();

	public MiscDatabase() {
		this.link = "misc";
	}
	
	static {
		data_sources.put("goals", new FootballGoalsSource());
		data_sources.put("temperature", new TemperatureSource());
		data_sources.put("gold", new GoldPriceSource());
		data_sources.put("spectators", new SpectatorsSource());
	}

	@Override
	public String link() {
		return link;
	}

	@Override
	public String[][] dataSet() {
		return data_sources.entrySet().stream()
				.map(entry -> new String[] { entry.getKey(), entry.getValue().getName() })
				.toArray(String[][]::new);
	}

	@Override
	public DataSource getSource(String id) {
		return data_sources.get(id);
	}
	
	@Override
	public String toString() {
		return "Nostalgikällor";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((link == null) ? 0 : link.hashCode());
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
		MiscDatabase other = (MiscDatabase) obj;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		return true;
	}
	
	

}
