package servlet;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

public class GetGraphDataServletTest extends Mockito {

	private GetGraphDataServlet servlet;
	private HttpServletRequest request;
	private HttpServletResponse response;

	@Before
	public void setUp() throws Exception {
		servlet = new GetGraphDataServlet();
		request = mock(HttpServletRequest.class);       
		response = mock(HttpServletResponse.class);
	}

	@After
	public void tearDown() throws Exception {
		servlet = null;
	}
	
	@Ignore
	@Test
	public void testCorrectParameters() {
		when(request.getParameter("res")).thenReturn("Month");
		when(request.getParameter("database1")).thenReturn("ENTSOE");
		when(request.getParameter("value1")).thenReturn("DE_CONS");
		when(request.getParameter("database2")).thenReturn("ENTSOE");
		when(request.getParameter("value2")).thenReturn("FI_PROD");
		when(request.getParameter("startDate")).thenReturn("2003-06-06");
		when(request.getParameter("endDate")).thenReturn("2016-06-02");

		try {
			servlet.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(response.getContentType());
		
		assertEquals(response.getContentType(), "application/json;charset=UTF-8");
		//assertThat(emptyResponse.toString(), containsString("could not be parsed"));
	}

}
