package servlet;

import java.io.*;
import javax.servlet.http.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import servlet.ServletTest;

public class MyServletTest extends Mockito {
	
	// bara test kod, för att se hur det fungerar
	
	@Before
    public void setUp() {
		
	}

    @Test
    public void testServlet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("me");
        when(request.getParameter("password")).thenReturn("secret");
        PrintWriter writer = new PrintWriter("somefile.txt");
        when(response.getWriter()).thenReturn(writer);

        new ServletTest().doGet(request, response);

        verify(request, atLeast(1)).getParameter("username"); // only if you want to verify username was called...
        writer.flush(); // it may not have been flushed yet...
 
    }
}