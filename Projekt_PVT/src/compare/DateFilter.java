package compare;

import static java.util.stream.Collectors.toMap;

import java.time.LocalDate;
import java.util.Map;

public class DateFilter {

	public DataSource getFilteredData(DataSource source, LocalDate startDate, LocalDate endDate) {
		if (source == null || startDate == null || endDate == null) return null;
		
		Map<LocalDate, Double> result = filterData(source, startDate, endDate);

		return new FilteredDataSource(source, result);
	}

	private Map<LocalDate, Double> filterData(DataSource source, LocalDate start, LocalDate end) {
		return source.getData().entrySet()
				.stream()
				.filter(entry -> entry.getKey().isBefore(end) && entry.getKey().isAfter(start))
				.collect(toMap( Map.Entry::getKey, Map.Entry::getValue));
	}
	
	private class FilteredDataSource implements DataSource {
		private final DataSource source;
		private final Map<LocalDate, Double> data;

		public FilteredDataSource(DataSource source, Map<LocalDate, Double> data) {
			this.source = source;
			this.data = data;
		}

		@Override
		public String getName() {
			return source.getName();
		}

		@Override
		public String getUnit() {
			return source.getUnit();
		}

		@Override
		public Map<LocalDate, Double> getData() {
			return data;
		}
		
	}
}
