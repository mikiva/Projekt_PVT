package json;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

import org.junit.Test;

public class CollectionJsonParserTest {

	@Test
	public void turnCollectionToJsonObjectWithAnArray() {
		Collection<String> testList = new TreeSet<>();
		testList.add("first");
		testList.add("second");
		String json = 
				"{" + 
					"\"values\":[\"first\",\"second\"]" +
				"}";
		assertEquals(json, new CollectionJsonParser<>(testList).toJsonString());
	}

}
