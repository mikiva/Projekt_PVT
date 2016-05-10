package json;

import java.util.Arrays;

import compare.DataSource;
import compare.Resolution;

public final class JsonStringFactory {

	private JsonStringFactory() {
	}

	public static JsonString get(Resolution res, final DataSource... dataSources) {
		DataSource[] sources = filterNonNull(dataSources);
		switch (sources.length) {
		case 1:
			return new SingleDataSourceJson(sources[0], res);
		case 2:
			return new ComparedDataSourceJson(sources[0], sources[1], res);
		default:
			throw new IllegalArgumentException("Wrong number of datasources");
		}
	}

	private static DataSource[] filterNonNull(final DataSource... dataSources) {
		return Arrays.stream(dataSources).filter(source -> source != null).toArray(DataSource[]::new);
	}

}
