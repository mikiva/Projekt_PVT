package compare;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import domain.DataSource;
import domain.FootballGoalsSource;
import servlet.JsonFormatter;

public class SingleDataSource implements JSONbuilder{

	
	
	private DataSource src;

	public SingleDataSource(DataSource src) {
		this.src = src;
	}

	@Override
	public String toJsonString() {
		Map<LocalDate, Double> data = new TreeMap<>(src.getData());
		String[] result = data.entrySet().stream().map(this::turnToJavaScriptObject).toArray(String[]::new);
		return "{\"data\":" + Arrays.deepToString(result) + "}";
	}
	
	private String turnToJavaScriptObject(Map.Entry<LocalDate, Double> entry) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{");
		stringBuilder.append("\"x\":");
		stringBuilder.append(entry.getValue());
		stringBuilder.append(",\"date\":");
		stringBuilder.append("\"");
		stringBuilder.append(entry.getKey());
		stringBuilder.append("\"");
		stringBuilder.append("}");
		return stringBuilder.toString();
	}
	
	public static void main(String[] args) {
		DataSource src = new FootballGoalsSource();
		String jsonString = new SingleDataSource(src).toJsonString();
		System.out.println(new JsonFormatter().format(jsonString));
	}

}
