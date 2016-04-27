package compare;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import domain.DataSource;
import domain.FootballGoalsSource;
import domain.TemperatureSource;
import servlet.JsonFormatter;

public class DataSourceComparator implements JSONbuilder {

	public DataSource source1;
	public DataSource source2;
	public DataCollection result;
	
	public DataSourceComparator(DataSource source1, DataSource source2) {
		this.source1 = source1;
		this.source2 = source2;
		result = new DataCollectionBuilder(source1, source2, Resolution.DAY).getResult();
	}
	
	public DataSourceComparator(DataSource source1, DataSource source2, Resolution res) {
		this.source1 = source1;
		this.source2 = source2;
		result = new DataCollectionBuilder(source1, source2, res).getResult();
	}

	private String turnToJavaScriptObject(Map.Entry<String, MatchedDataPair> entry) {
		return "{" +
				"\"x\":" + entry.getValue().getXValue() +
				",\"y\":" + entry.getValue().getYValue() + 
				",\"date\":" +  "\"" + entry.getKey() + "\"" +
			"}";
	}
	
	@Override
	public String toJsonString() {
		Map<String, MatchedDataPair> data = new TreeMap<>(result.getData());
		String[] dataObjects = data.entrySet().stream().map(this::turnToJavaScriptObject).toArray(String[]::new);

		return "{" + getSourceName(source1, "xName") + 
					getSourceName(source2, "yName") + 
					getSourceUnit(source1, "xUnit") + 
					getSourceUnit(source2, "yUnit") +
					"\"data\":"+ Arrays.toString(dataObjects) + "}";
	}
	
	private String getSourceUnit(DataSource source, String name) {
		return "\"" + name + "\":\"" + source.getUnit() + "\",";
	}

	private String getSourceName(DataSource source, String name) {
		return "\"" + name + "\":\"" + source.getName() + "\",";
	}
	
	public static void main(String[] args) {
		FootballGoalsSource source22 = new FootballGoalsSource();
		TemperatureSource source12 = new TemperatureSource();
		DataSourceComparator dataSourceComparator = new DataSourceComparator(source12, source22);
		JsonFormatter jsonFormatter = new JsonFormatter();
		System.out.println(jsonFormatter.format(dataSourceComparator.toJsonString()));
	}
}
