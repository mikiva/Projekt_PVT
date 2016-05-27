package json;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CollectionJsonParser<T> implements JsonString {

	private final Collection<T> list;

	public CollectionJsonParser(Collection<T> list) {
		this.list = list;
	}

	@Override
	public String toJsonString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"values\":");
		String[] values = list.stream().map(element -> "\"" + element + "\"").toArray(String[]::new);
		String valuesWithWhiteSpaceRemoved = Arrays.toString(values).replaceAll("\\s+","");
		builder.append(valuesWithWhiteSpaceRemoved + "}");
		return builder.toString();
	}

}
