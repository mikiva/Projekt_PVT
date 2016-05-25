package json;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ListJsonParserTest {

	@Test
	public void turnListToJsonObjectWithAnArray() {
		List<String> testList = new ArrayList<>();
		testList.add("first");
		testList.add("second");
		String json = 
				"{" + 
					"values:[\"first\",\"second\"]" +
				"}";
		assertEquals(json, new ListJsonParser<>(testList).toJsonString());
	}

}
