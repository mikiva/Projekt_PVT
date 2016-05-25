package json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListJsonParser<T> implements JsonString {

	private final List<T> list;

	public ListJsonParser(List<T> list) {
		this.list = list;
	}

	@Override
	public String toJsonString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{values:");
		String[] values = list.stream().map(element -> "\"" + element + "\"").toArray(String[]::new);
		String valuesWithWhiteSpaceRemoved = Arrays.toString(values).replaceAll("\\s+","");
		builder.append(valuesWithWhiteSpaceRemoved + "}");
		return builder.toString();
	}
	
	public static void main(String[] args) {
		List<Double> list = new ArrayList<>();
		list.add(54654d);
		list.add(131242d);
		JsonString js = new ListJsonParser<>(list);
		System.out.println(js.toJsonString());
	}

}
