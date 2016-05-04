package json;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import compare.DataCollection;
import compare.DataCollectionBuilder;
import compare.DataSource;
import compare.MatchedDataPair;
import compare.Resolution;

public class ComparedDataSourceJson implements JsonString {

	public DataSource source1;
	public DataSource source2;
	public DataCollectionBuilder builder;
	public DataCollection result;
	
	public ComparedDataSourceJson(DataSource source1, DataSource source2) {
		this.source1 = source1;
		this.source2 = source2;
		builder = new DataCollectionBuilder(source1, source2, Resolution.DAY);
		result = builder.getResult();
	}
	
	public ComparedDataSourceJson(DataSource source1, DataSource source2, Resolution res) {
		this.source1 = source1;
		this.source2 = source2;
		builder = new DataCollectionBuilder(source1, source2, res);
		result = builder.getResult();
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
}
