package database.misc;

import static org.junit.Assert.*;

import org.junit.Test;

public class GoldPriceSourceTest {

	@Test
	public void nameIsAsExpected() {
		assertEquals("Gold prices (EUR)", new GoldPriceSource().getName());
	}
	
	@Test
	public void unitIsAsExpected() throws Exception {
		assertEquals("EUR per ounce", new GoldPriceSource().getUnit());
	}

}
