package compare;

import domain.enums.Databases;
import json.JsonString;

public class DataBasesComparator implements JsonString {
	
	public Databases[] getValues(){
		return Databases.values();
	}

	public String toJsonString() {
		StringBuilder result = new StringBuilder();
		result.append("{\"data\":[");
					for (Databases db : getValues()) {
						result.append("{\"database\":\"" + db.toString() + "\",");
						result.append("\"values\":[");
						for (String[] string: db.getDataSets()) {
							result.append("[");
							result.append("\"" + string[0] + "\",");
							result.append("\"" + string[1] + "\"");
							result.append("],");
						}
						result.deleteCharAt(result.length() - 1);
						result.append("]},");
					}
		result.deleteCharAt(result.length() - 1);
		result.append("]}");			
		
		return result.toString();
	}

}