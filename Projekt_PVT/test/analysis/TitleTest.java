package analysis;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import analysis.Title.TitleException;

public class TitleTest {

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
	
	@Test
	public void naturalOrderIsTheSameAsString() throws Exception {
		Title t1 = new Title("a");
		Title t2 = new Title("b");
		Title t3 = new Title("c");
		
		List<Title> titles = new ArrayList<>();
		titles.addAll(Arrays.asList(t2, t3, t1));
		Collections.sort(titles);
		
		assertEquals("first title should be \"a\"", t1, titles.get(0));
		assertEquals("second title should be \"b\"", t2, titles.get(1));
		assertEquals("third title should be \"c\"", t3, titles.get(2));
	}

}
