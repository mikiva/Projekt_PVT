package analysis;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommentTest {

	@Test
	public void testToString() {
		Comment comment = new Comment("testkommentar osv.");
		assertEquals("testkommentar osv.", comment.toString());
	}
	
	@Test
	public void creatingCommentWithNullAsParameterMakesToStringReturnEmptyString() throws Exception {
		Comment comment = new Comment(null);
		assertEquals("", comment.toString());
	}

}
