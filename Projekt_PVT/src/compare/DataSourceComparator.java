package compare;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import domain.DataSource;
import domain.FootballGoalsSource;
import domain.GoldPriceSource;
import domain.SpectatorsSource;
import domain.StaticJSONSource;

public class DataSourceComparator implements JSONbuilder {

	public DataSource source1;
	public DataSource source2;
	public DataCollectionBuilder builder;
	public DataCollection result;
	
	public DataSourceComparator(DataSource source1, DataSource source2) {
		this.source1 = source1;
		this.source2 = source2;
		builder = new DataCollectionBuilder(source1, source2, Resolution.DAY);
		result = builder.getResult();
	}
	
	public DataSourceComparator(DataSource source1, DataSource source2, Resolution res) {
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
		String[] result = data.entrySet().stream().map(this::turnToJavaScriptObject).toArray(String[]::new);
		return "{\"data\":"+ Arrays.deepToString(result) + "}";
	}
}
