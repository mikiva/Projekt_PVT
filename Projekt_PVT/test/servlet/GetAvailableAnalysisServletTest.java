package servlet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Ignore;
import org.junit.Test;

import analysis.Title;
import analysis.database.SqlDatabase;

public class GetAvailableAnalysisServletTest {
	
	private static final String EXPECTED = "{\"values\":[\"test\"]}";

	@Test
	@Ignore
	public void getJsonStringOfSavedTitles() throws Exception {
		SqlDatabase mockDB = mock(SqlDatabase.class);
		GetAvailableAnalysisServlet servlet = new GetAvailableAnalysisServlet(mockDB);
		Set<Title> savedTitles = new HashSet<>();
		savedTitles.add(new Title("test"));

		when(mockDB.getSavedTitles()).thenReturn(savedTitles);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);
		
		servlet.doGet(request, response);
		
		assertEquals(EXPECTED, stringWriter.toString());
	}

}
