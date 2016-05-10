package compare;

import static org.junit.Assert.*;

import org.junit.Test;

public class MatchedDataPairTest {

	private MatchedDataPair createInstance() {
		return new MatchedDataPair(5.0, 2.0);
	}
	
	@Test
	public void testGetXValue() {
		MatchedDataPair matchedDataPair = createInstance();
		assertEquals(5.0, matchedDataPair.getXValue(), 0.01);
	}
	
	@Test
	public void testGetYValue() throws Exception {
		MatchedDataPair mdp = createInstance();
		assertEquals(2.0, mdp.getYValue(), 0.01);
	}


}
