package database.misc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;

public class SpectatorsSourceTest {

	@Test
	public void nameTest() throws Exception {
		assertEquals("Spectators", new SpectatorsSource().getName());
	}
	
	@Test
	public void getCorrectUnit() throws Exception {
		assertEquals("Antal åskådare", new SpectatorsSource().getUnit());
	}
	
	@Test
	public void dataIsNotNull() throws Exception {
		assertNotNull(new SpectatorsSource().getData());
	}

}
