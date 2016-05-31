package servlet;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class SaveAnalyzeServletTest extends Mockito {
	
	SaveAnalyzeServlet servlet;
	HttpServletRequest emptyRequest;
	HttpServletResponse emptyResponse;
	
	@Before
    public void setUp() {
		servlet = new SaveAnalyzeServlet();
		emptyRequest = mock(HttpServletRequest.class);       
        emptyResponse = mock(HttpServletResponse.class);   
	}

	@Test 
	public void testEmpty () {
		 when(emptyRequest.getParameter("title")).thenReturn("");
		 when(emptyRequest.getParameter("res")).thenReturn("");
		 when(emptyRequest.getParameter("startDate")).thenReturn("");
		 when(emptyRequest.getParameter("endDate")).thenReturn("");
		 when(emptyRequest.getParameter("database1")).thenReturn("");
		 when(emptyRequest.getParameter("value1")).thenReturn("");
		 when(emptyRequest.getParameter("database2")).thenReturn("");
		 when(emptyRequest.getParameter("value2")).thenReturn("");
		 when(emptyRequest.getParameter("comment")).thenReturn("");
		 
		 try {
			servlet.doGet(emptyRequest, emptyResponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		 assertTrue(emptyResponse.toString() != "");
	}
	
}
