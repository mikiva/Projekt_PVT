package json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import compare.DataSource;
import compare.Resolution;
public class SingleDataSourceTest {
	
	private SingleDataSourceJson src;
	private DataSource data;
	private Map<LocalDate, Double> map;
	private String expectedJson = "{\"2014-02-02\":75.5,\"2014-03-29\":435.4,\"2014-05-26\":123.3,\"2014-09-11\":642.4,\"2014-12-21\":152.3,\"2014-12-23\":105.5}";
	private Resolution res = Resolution.DAY;

	@Before
	public void setUp() throws Exception {
		
		
		data = mock(DataSource.class);
		src = new SingleDataSourceJson(data, res);
		map = new TreeMap<>();
		when(src.toJsonString()).thenReturn(expectedJson);
		
		map.put(LocalDate.parse("2014-05-26"), 123.3);
		map.put(LocalDate.parse("2014-12-21"), 152.3);
		map.put(LocalDate.parse("2014-09-11"), 642.4);
		map.put(LocalDate.parse("2014-02-02"), 75.5);
		map.put(LocalDate.parse("2014-03-29"), 435.4);
		map.put(LocalDate.parse("2014-12-23"), 105.5);
		
		
	}

	@Test
	public void getJsonStringTest() throws Exception {
		
		//assertEquals(src.toJsonString().getClass(), String.class);
	}
	
	@Test
	public void checkJsonStringTest() throws Exception {
		
		assertTrue(src.toJsonString().contains("{"));
		
	}

}
