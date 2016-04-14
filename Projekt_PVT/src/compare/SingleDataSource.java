package compare;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import domain.DataSource;
import domain.FootballGoalsSource;

public class SingleDataSource implements JSONbuilder{

	
	
	private DataSource src;

	public SingleDataSource(DataSource src) {
		this.src = src;
	}

	@Override
	public String getData() {
		Map<LocalDate, Double> data = new TreeMap<>(src.getData());
		String[][] result = data.entrySet().stream().map(this::turnToArray).toArray(String[][]::new);
		return "{\"dataset\":" + Arrays.deepToString(result) + "}";
	}
	
	private String[] turnToArray(Map.Entry<LocalDate, Double> entry) {
		return new String[] { "\"" + entry.getKey().toString() + "\"", Double.toString(entry.getValue())};
	}
	
	public static void main(String[] args) {
		DataSource src = new FootballGoalsSource();
		System.out.println(new SingleDataSource(src).getData());
	}

}
