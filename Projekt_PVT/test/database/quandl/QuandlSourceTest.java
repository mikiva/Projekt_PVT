package database.quandl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import compare.DataSource;

public class QuandlSourceTest {

	private DataSource quandl;

	@Before
	public void setUp() {
		quandl = QuandlSource.createFrom("ODA", "SWE_LP", new SourceProperties("Sweden Population, Millions", "Persons"));
	}
	
	@Test
	public void getCorrectUnitFromSource() throws Exception {
		assertEquals("Persons", quandl.getUnit());
		assertEquals("US Dollars per Metric Ton",
				QuandlSource.createFrom("ODA", "PBANSOP_USD", 
				new SourceProperties("S", "US Dollars per Metric Ton")).getUnit());
	}
	
	@Test
	public void getCorrectNameFromSource() throws Exception {
		assertEquals("Sweden Population, Millions", quandl.getName());
		assertEquals("Bananas Price", QuandlSource.createFrom("ODA", "PBANSOP_USD",
				new SourceProperties("Bananas Price", "US Dollars per Metric Ton")).getName());
	}
	
	@Test
	public void retrievingDataFromSource() throws Exception {
		assertNotNull(quandl.getData());
		assertTrue("entries in dataset", quandl.getData().size() > 0);
	}

}
