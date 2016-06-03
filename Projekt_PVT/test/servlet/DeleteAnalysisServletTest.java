package servlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import analysis.Title;
import analysis.database.SqlDatabase;

public class DeleteAnalysisServletTest {

	private DeleteAnalysisServlet servlet;
	private Set<Title> savedTitles;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private StringWriter stringWriter;
	
	private static final String TITLE_NAME = "test";

	@Before
	public void setUp() throws Exception {
		SqlDatabase mockDB = mock(SqlDatabase.class);
		servlet = new DeleteAnalysisServlet(mockDB);
		savedTitles = new HashSet<>();

		when(mockDB.getSavedTitles()).thenReturn(savedTitles);
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);

		stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

	}

	@Test
	public void deleteAnalysisSuccessfully() throws Exception {
		when(request.getParameter("title")).thenReturn(TITLE_NAME);
		savedTitles.add(new Title(TITLE_NAME));
		servlet.doGet(request, response);

		assertEquals("Analysis " + TITLE_NAME + " deleted", stringWriter.toString());
	}

	@Test
	public void tryToDeleteAnalysisThatDoesNotExist() throws Exception {
		when(request.getParameter("title")).thenReturn(TITLE_NAME);
		servlet.doGet(request, response);
		assertEquals("Analysis with that title does not exists", stringWriter.toString());
	}
	
	@Test
	public void errorWhenNotUsingParameterWithRequest() throws Exception {
		servlet.doGet(request, response);
		assertEquals("Title cannot be empty!", stringWriter.toString());
	}

}
