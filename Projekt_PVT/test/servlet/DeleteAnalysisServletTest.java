package servlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import analysis.Title;
import analysis.database.SqlDatabase;

public class DeleteAnalysisServletTest {

	@Test
	public void deleteAnalysisSuccessfully() throws IOException {
		SqlDatabase mockDB = mock(SqlDatabase.class);
		Title title = new Title("test");
		Set<Title> savedTitles = new HashSet<>();
		savedTitles.add(new Title("test"));
		when(mockDB.getSavedTitles()).thenReturn(savedTitles);
		PrintWriter writer = new PrintWriter(new StringWriter());
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		when(req.getParameter("title")).thenReturn("test");
		when(resp.getWriter()).thenReturn(writer);
		
	}

}
