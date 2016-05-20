package analysis;

import static org.junit.Assert.*;

import org.junit.Test;

import analysis.Title.TitleException;

public class AnalysisTitleTest {

	@Test(expected = TitleException.class)
	public void titleCannotBeNull() {
		new Title(null);
	}
	
	@Test(expected = TitleException.class)
	public void titleCannotBeEmpty() throws Exception {
		new Title("");
	}
	
	@Test
	public void toStringReturnsTheTitle() throws Exception {
		String title = "Title";
		assertEquals(title, new Title(title).toString());
	}

}
