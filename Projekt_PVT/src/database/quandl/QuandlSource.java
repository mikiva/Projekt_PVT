package database.quandl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import compare.DataSource;
import database.UrlFetcher;
import json.JsonToMapParser;

class QuandlSource implements DataSource {
	private final Map<String, Object> dataset;
	private final SourceProperties properties;

	private final static String apiKey = "?api_key=RyFTK4mXiPZoDZViXzsS";
	
	private QuandlSource(Map<String, Object> dataset, SourceProperties properties) {
		this.dataset = dataset;
		this.properties = properties;
	}
	
	public static DataSource createFrom(String dataBase, String dataSet, SourceProperties sourceProperties) {
		Map<String, Object> dataset = createJsonDataset(dataBase, dataSet);
		return new QuandlSource(dataset, sourceProperties);
	}

	@SuppressWarnings("unchecked")
	private static Map<String, Object> createJsonDataset(String dataBase, String dataSet) {
		String link = "https://www.quandl.com/api/v3/datasets/" + dataBase
				+ "/" + dataSet + ".json" + apiKey;
		
		UrlFetcher fetcher = new UrlFetcher(link);
		JsonToMapParser parser = new JsonToMapParser(fetcher.getContent());
		return (Map<String, Object>) parser.getResult().get("dataset");
	}
	
	@Override
	public String getName() {
		return this.properties.name(); 
	}

	@Override
	public String getUnit() {
		return this.properties.unit();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<LocalDate, Double> getData() {
		List<List> data = (List<List>) this.dataset.get("data");
		
		return data.stream().collect(Collectors.toMap(
				list -> LocalDate.parse(list.get(0).toString()), 
				list -> Double.parseDouble(list.get(1).toString())));
	}



}