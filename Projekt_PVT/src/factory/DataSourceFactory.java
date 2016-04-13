package factory;

import java.util.HashMap;
import java.util.Map;

import domain.DataSource;
import domain.FootballGoalsSource;
import domain.GoldPriceSource;
import domain.SpectatorsSource;
import domain.StaticJSONSource;
import domain.TemperatureSource;

public final class DataSourceFactory {
	private static final Map<String, Class<? extends DataSource>> data_sources = new HashMap<>();
	
	static {
		data_sources.put("goals", FootballGoalsSource.class);
		data_sources.put("temperature", TemperatureSource.class);
		data_sources.put("gold", GoldPriceSource.class);
		data_sources.put("spectators", SpectatorsSource.class);
		data_sources.put("static", StaticJSONSource.class);
	}
	
	private DataSourceFactory() {
	}

	public static DataSource get(String source) {
		try {
			return data_sources.get(source).newInstance();
		} catch (InstantiationException | IllegalAccessException | NullPointerException e) {
			throw new FactoryException("Could not instantiate new class using string \"" + source + "\"");
		}
	}
	
}
