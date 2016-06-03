package servlet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import analysis.database.SqlDatabase;
import analysis.database.SqlTable;

public class SaveAnalyzeServletTest extends Mockito {
	
	private SaveAnalyzeServlet servlet;
	private HttpServletRequest emptyRequest;
	private HttpServletResponse emptyResponse;
	private SqlTable table;
	private SqlDatabase sql;
	
	@Before
    public void setUp() {
		emptyRequest = mock(HttpServletRequest.class);       
        emptyResponse = mock(HttpServletResponse.class);
        table = mock(SqlTable.class);
		sql = new SqlDatabase(table);
		
		servlet = new SaveAnalyzeServlet();
	}
	
	@Ignore
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
		
		assertEquals(emptyResponse, null);
	}
	
}
