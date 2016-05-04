package json;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import compare.DataSource;

public class SingleDataSourceJson implements JsonString{

	
	
	private DataSource src;

	public SingleDataSourceJson(DataSource src) {
		this.src = src;
	}

	@Override
	public String toJsonString() {
		Map<LocalDate, Double> data = new TreeMap<>(src.getData());
		String[] result = data.entrySet().stream().map(this::turnToJavaScriptObject).toArray(String[]::new);
		return "{\"name\":\"" + src.getName() + "\", \"unit\":\"" + src.getUnit() + "\",\"data\":" + Arrays.toString(result) + "}";
	}
	
	private String turnToJavaScriptObject(Map.Entry<LocalDate, Double> entry) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{");
		stringBuilder.append("\"y\":");
		stringBuilder.append(entry.getValue());
		stringBuilder.append(",\"date\":");
		stringBuilder.append("\"");
		stringBuilder.append(entry.getKey());
		stringBuilder.append("\"");
		stringBuilder.append("}");
		return stringBuilder.toString();
	}
	
	public static void main(String[] args) {
		//new SingleDataSourceJson(JsonStringFactory.get(dataSources))
	}
	
}
