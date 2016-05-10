package database.quandl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SourcePropertiesTest {

	
	private SourceProperties createSourceProperties() {
		return new SourceProperties("namn", "enhet");
	}
	
	@Test
	public void getName() throws Exception {
		SourceProperties sp = createSourceProperties();
		assertEquals("namn", sp.name());
	}
	
	@Test
	public void getUnit() throws Exception {
		SourceProperties sp = createSourceProperties();
		assertEquals("enhet", sp.unit());
	}

}
