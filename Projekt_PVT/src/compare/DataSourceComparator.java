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

	private String[] turnToArray(MatchedDataPair pair) {
		return new String[] { pair.getXValue() + "," + pair.getYValue() };
		
	}
	
	@Override
	public String toJsonString() {
		
		Map<String, MatchedDataPair> data = new TreeMap<>(result.getData());
		String[][] result = data.values().stream().map(this::turnToArray).toArray(String[][]::new);
		return "{"+ Arrays.deepToString(result) + "}";
		
		//return new Genson().serialize(result.getData());
//		StringBuilder sb = new StringBuilder();
//		
//		sb.append("{");
//		sb.append("\"Data\": [");
//		
//		result.getData().forEach((date, match) -> {
//			sb.append(
//		            "{\"Date\":" + "\"" + date + "\"" + "," + 
//					"\"" + source1.getName() + "\":" + match.getXValue() + "," +
//					"\"" + source2.getName() + "\":" + match.getYValue() + " },");
//		});
//		sb.deleteCharAt(sb.length() - 1);
//		sb.append("]}");
//		
//		return sb.toString();
	}
	
	public static void main(String[] args) {
		DataSource a = new FootballGoalsSource();
		DataSource c = new GoldPriceSource();
		
		JSONbuilder comp = new DataSourceComparator(c, a);
		
		System.out.println(comp.toJsonString());
	}
	
}
