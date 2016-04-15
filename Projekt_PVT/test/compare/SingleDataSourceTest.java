package compare;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.DataSource;
import domain.SpectatorsSource;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
public class SingleDataSourceTest {
	
	private SingleDataSource src;
	private DataSource data;
	private Map<LocalDate, Double> map;
	private String expectedJson = "{\"2014-02-02\":75.5,\"2014-03-29\":435.4,\"2014-05-26\":123.3,\"2014-09-11\":642.4,\"2014-12-21\":152.3,\"2014-12-23\":105.5}";
	

	@Before
	public void setUp() throws Exception {
		
		
		data = mock(DataSource.class);
		src = new SingleDataSource(data);
		map = new TreeMap<>();
		
		map.put(LocalDate.parse("2014-05-26"), 123.3);
		map.put(LocalDate.parse("2014-12-21"), 152.3);
		map.put(LocalDate.parse("2014-09-11"), 642.4);
		map.put(LocalDate.parse("2014-02-02"), 75.5);
		map.put(LocalDate.parse("2014-03-29"), 435.4);
		map.put(LocalDate.parse("2014-12-23"), 105.5);
		
		
	}

	@Test
	public void getJsonStringTest() throws Exception {
		
		assertEquals(src.toJsonString().getClass(), String.class);
	}
	
	@Test
	public void checkJsonStringTest() throws Exception {
		
		assertTrue(src.toJsonString().contains("{"));
		
	}
	
	
	public void getResultFromSourceTest() throws Exception {
		
		when(data.getData()).thenReturn(map);
		
		assertEquals(src.toJsonString(), expectedJson);
		
		
	}
}
