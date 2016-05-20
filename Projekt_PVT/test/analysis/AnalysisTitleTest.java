package analysis;

import static org.junit.Assert.*;

import org.junit.Test;

import analysis.AnalysisTitle.TitleException;

public class AnalysisTitleTest {

	@Test(expected = TitleException.class)
	public void titleCannotBeNull() {
		new AnalysisTitle(null);
	}
	
	@Test(expected = TitleException.class)
	public void titleCannotBeEmpty() throws Exception {
		new AnalysisTitle("");
	}
	
	@Test
	public void toStringReturnsTheTitle() throws Exception {
		String title = "Title";
		assertEquals(title, new AnalysisTitle(title).toString());
	}

}
