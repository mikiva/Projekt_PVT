package database.misc;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class FootballGoalsSourceTest {

	private FootballGoalsSource goalsSource;
	private Map<LocalDate, Double> csvMap;
	
	@Before
	public void setUp() throws Exception {
		goalsSource = new FootballGoalsSource();
		csvMap = goalsSource.getData();
	}

	@Test
	@Ignore
	public void testGetResultKeysAndValues() {
		LocalDate firstDate = LocalDate.parse("2014-04-06");
		Double firstResult = 3.0;
		LocalDate lastDate = LocalDate.parse("2014-10-19");
		Double lastResult = 3.0;
		LocalDate anyDate = LocalDate.parse("2014-05-26");
		Double anyResult = 2.0;
		
		assertEquals(firstResult, csvMap.get(firstDate));
		assertEquals(lastResult, csvMap.get(lastDate));
		assertEquals(anyResult, csvMap.get(anyDate));
	} 
	
	@Test
	@Ignore
	public void testSizeOfCsvData() {
		int csvDataSize = 14;
		assertEquals(csvDataSize, csvMap.size());
	}
	
	@Test
	@Ignore
	public void testIncorrectValue() {
		assertEquals(null, csvMap.get(LocalDate.parse("2666-01-01")));
	}
	
}
