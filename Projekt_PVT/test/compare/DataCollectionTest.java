package compare;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class DataCollectionTest {

	private static final Map<String, MatchedDataPair> data = new HashMap<>();
	static {
		data.put("2005-05-12", new MatchedDataPair(5.0, 51.0));
		data.put("2012-02-09", new MatchedDataPair(5.0, 1.123));
		data.put("1995-03-28", new MatchedDataPair(3.0, 7.0));
	}

	private DataCollection createDataCollection() {
		DataCollection coll = new DataCollection("Titel", "xUnit", "yUnit", data);
		return coll;
	}

	@Test
	public void getTitleOfDataCollection() throws Exception {
		DataCollection coll = createDataCollection();
		assertEquals("Titel", coll.getTitle());
		assertNotEquals("title", coll.getTitle());
	}

	@Test
	public void getXUnit() throws Exception {
		DataCollection coll = createDataCollection();
		assertEquals("xUnit", coll.getXUnit());
	}

	@Test
	public void getYUnit() throws Exception {
		DataCollection coll = createDataCollection();
		assertEquals("yUnit", coll.getYUnit());
	}

	@Test
	public void getData() throws Exception {
		DataCollection coll = createDataCollection();
		assertEquals(data, coll.getData());
	}
}
