package json;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import compare.DataSource;
import compare.Resolution;

import java.util.Map.Entry;


public class SingleDataSourceJson implements JsonString {

	private DataSource src;
	private Resolution res;
	private Map<LocalDate, Double> data;
	private Map<String, List<Double>> resultData;
	private Map<String, Double> finalResult;
	private List<Double> sameDateValues;

	public SingleDataSourceJson(DataSource src) {
		this(src, Resolution.DAY);
	}

	public SingleDataSourceJson(DataSource src, Resolution res) {
		this.src = src;
		this.res = res;
		this.finalResult = new TreeMap<>();
		this.resultData = new TreeMap<>();
		this.sameDateValues = new ArrayList<>();

	}

	@Override
	public String toJsonString() {
		data = new TreeMap<>(src.getData());
		
		if (!res.equals(Resolution.DAY)){
			modifyDataAfterRes();
			createFinalFromResult();
		}
		else{
			for(Entry<LocalDate, Double> map : data.entrySet()){
				finalResult.put(map.getKey().toString(), map.getValue());
			}
		}
		String[] result = finalResult.entrySet().stream().map(this::turnToJavaScriptObject).toArray(String[]::new);
		return "{\"name\":\"" + src.getName() + "\", \"unit\":\"" + src.getUnit() + "\",\"data\":"
		+ Arrays.deepToString(result) + "}";
	}

	private void modifyDataAfterRes() {

		String previousDate = ""; 
		String currentDate = "";
		for (Entry<LocalDate, Double> size : data.entrySet()) {

			currentDate = res.getLocalDate(size.getKey());
			if(previousDate == ""|| res.getLocalDate(size.getKey()).equals(previousDate)) {
				previousDate = res.getLocalDate(size.getKey());
				System.out.println(res.getLocalDate(size.getKey()));

				sameDateValues.add(size.getValue());


			}
			else{



				resultData.put(previousDate, sameDateValues);
				System.out.println(resultData);
				this.sameDateValues = new ArrayList<>();

				sameDateValues.add(size.getValue());
				previousDate = "";

			}


		}
		resultData.put(currentDate, sameDateValues);

		System.out.println(resultData);

	}

	private void addToMatchDates(Double value){


		sameDateValues.add(value);

	}

	private void createFinalFromResult() {

		for (Entry<String, List<Double>> map : resultData.entrySet()){

			Double sum = 0.0;


			for (int i = 0; i < map.getValue().size(); i++) {
				sum += map.getValue().get(i);

			}
			Double averageX = sum / map.getValue().size();

			finalResult.put(map.getKey(), roundTwoDecimals(averageX));

		}
	}
	private double roundTwoDecimals(double d){
		return ((double) Math.round(d * 100) / 100);
	}




	private String turnToJavaScriptObject(Map.Entry<String, Double> entry) {
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

}