package domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class QuandlSource implements DataSource {
	//http://localhost:8080/Proj/ServletTest?datasource=quandl&database1=ODA&values1=SWE_LE&datasource=quandl&database2=ODA&values2=PBANSOP_USD
	private String LINK = "";
	private String dataBase;
	private String dataSet;

	@Override
	public String getName() {
		return "Banana price"; 
	}

	@Override
	public String getUnit() {
		return "Dollars/metric ton";
	}
	
	public String getDataBase() {
		return dataBase;
	}
	
	public String getDataSet() {
		return "PBANSOP_USD";
	}
	
	public void setParameters(String dataBase, String dataSet) {
		this.dataBase = dataBase;
		this.dataSet = dataSet;
		
		LINK = "https://www.quandl.com/api/v3/datasets/" + dataBase
		+ "/" + dataSet + ".json";
	}

	@Override
	public Map<LocalDate, Double> getData() {
		UrlFetcher fetcher = new UrlFetcher(LINK);
		JsonToMapParser parser = new JsonToMapParser(fetcher.getContent());
		
		Map<String, Object> dataset = (Map) parser.getResult().get("dataset");
		List<List> data = (List<List>) dataset.get("data");
		
		List<LocalDate> dates = data.stream()
				.map(list -> LocalDate.parse(list.get(0).toString()))
				.collect(Collectors.toList());
		List<Double> values = data.stream()
				.map(list -> Double.parseDouble(list.get(1).toString()))
				.collect(Collectors.toList());
		
		Map<LocalDate, Double> result = new TreeMap<>();
		for (int i = 0; i < dates.size(); i++) {
			result.put(dates.get(i), values.get(i));
		}
		
		return result;
	}
}