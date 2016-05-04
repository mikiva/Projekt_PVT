package json;

import database.Database;
import database.DatabaseFactory;

public class AvailableDatabases implements JsonString {
	

	@Override
	public String toJsonString() {
		StringBuilder result = new StringBuilder();
		result.append("{\"data\":[");
					for (Database db : DatabaseFactory.all()) {
						result.append("{\"database\":\"" + db.toString() + "\",");
						result.append("\"values\":[");
						for (String[] string: db.dataSet()) {
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
