package database.misc;

import java.util.Map;
import java.util.TreeMap;

import database.UrlFetcher;

class CsvToMapParser {
	
	private final String link;

	public CsvToMapParser(String link) {
		this.link = link;
	}
	
	public Map<String, String> getResult() {
		UrlFetcher url = new UrlFetcher(link);
		String csv = url.getContent();
		Map<String, String> map = new TreeMap<>();
	    String[] line =  csv.split(";");

	    for(int i = 0; i < line.length; i++){
	    	if(line[i].startsWith("Y"))
	    		map.put(line[i - 2], line[i - 1]);
	    }
	    return map;
	}

}
