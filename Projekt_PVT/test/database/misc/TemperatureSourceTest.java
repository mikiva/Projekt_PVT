package database.misc;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class TemperatureSourceTest {

	private TemperatureSource temperatureSource;
	private Map<LocalDate, Double> csvMap;
	
	@Before
	public void setUp() throws Exception {
		temperatureSource = new TemperatureSource();
		csvMap = temperatureSource.getData();
	}

	@Test
	public void getSomeKnownValues() {
		LocalDate firstDate = LocalDate.parse("1995-08-01");
		Double firstResult = 19.1;
		LocalDate lastDate = LocalDate.parse("2015-10-31");
		Double lastResult = 8.0;
		LocalDate anyDate = LocalDate.parse("2005-11-17");
		Double anyResult = -4.8;
		
		assertEquals(firstResult, csvMap.get(firstDate));
		assertEquals(lastResult, csvMap.get(lastDate));
		assertEquals(anyResult, csvMap.get(anyDate));
	} 
	
	@Test
	public void thereIsDataInDataSource() throws Exception {
		assertTrue(!csvMap.isEmpty());
	}
	
	@Test
	public void testIncorrectValue() {
		assertEquals(null, csvMap.get("Finns inte"));
	}
}
