package compare;

import domain.enums.Databases;

public class DataBasesComparator implements JSONbuilder {
	
	public Databases[] getValues(){
		return Databases.values();
	}

	@Override
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