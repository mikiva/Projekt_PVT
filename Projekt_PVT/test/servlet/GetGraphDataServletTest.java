package servlet;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GetGraphDataServletTest extends Mockito {

	private GetGraphDataServlet servlet;
	private HttpServletRequest emptyRequest;
	private HttpServletResponse emptyResponse;

	@Before
	public void setUp() throws Exception {
		servlet = new GetGraphDataServlet();
		emptyRequest = mock(HttpServletRequest.class);       
		emptyResponse = mock(HttpServletResponse.class);
	}

	@After
	public void tearDown() throws Exception {
		servlet = null;
	}

	@Test
	public void testEmptyParameters() {

		when(emptyRequest.getParameter("title")).thenReturn("");
		when(emptyRequest.getParameter("res")).thenReturn("");
		when(emptyRequest.getParameter("startDate")).thenReturn("");
		when(emptyRequest.getParameter("endDate")).thenReturn("");
		when(emptyRequest.getParameter("database1")).thenReturn("");
		when(emptyRequest.getParameter("value1")).thenReturn("");
		when(emptyRequest.getParameter("database2")).thenReturn("");
		when(emptyRequest.getParameter("value2")).thenReturn("");

		try {
			servlet.doGet(emptyRequest, emptyResponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
