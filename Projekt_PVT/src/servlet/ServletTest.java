package servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import compare.DataSource;
import database.DatabaseFactory;
import json.JsonFormatter;
import json.JsonStringFactory;

/**
 * Servlet implementation class ServletTest
 */
@WebServlet("/ServletTest")
public class ServletTest extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public ServletTest() {
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("application/json;charset=UTF-8");
        JsonFormatter format = new JsonFormatter();
        
        Boolean pretty = Boolean.valueOf(request.getParameter("pretty"));
        String database1 = request.getParameter("database1");
        String database2 = request.getParameter("database2");
        String value1 = request.getParameter("value1");
        String value2 = request.getParameter("value2");
        
        System.out.println(database1 + ", " + database2 + ", " + value1 + ", " + value2);
               
        try{   	
        	DataSource source1 = DatabaseFactory.get(database1).getSource(value1);
        	DataSource source2 = DatabaseFactory.get(database2).getSource(value2);
        	String json = JsonStringFactory.get(source1, source2).toJsonString();
        	response.getWriter().append(pretty ? format.format(json): json);
        }
        catch(RuntimeException e){
        	response.getWriter().append(e.getMessage());
        }    
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}