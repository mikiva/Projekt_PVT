package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import compare.DataSourceComparator;
import compare.JSONbuilder;
import domain.DataSource;
import domain.QuandlSource;
import factory.DataSourceFactory;
import factory.JSONbuilderFactory;

/**
 * Servlet implementation class ServletTest
 */
@WebServlet("/ServletTest")
public class ServletTest extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private JSONbuilderFactory jsonBuilder;
	
    /**
     * Default constructor. 
     */
    public ServletTest() {
    	
    	jsonBuilder = new JSONbuilderFactory();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("application/json;charset=UTF-8");
        Boolean pretty = Boolean.valueOf(request.getParameter("pretty"));
        JsonFormatter format = new JsonFormatter();
        
        String datasource[] = request.getParameterValues("datasource");
        String database1 = request.getParameter("database1");
        String database2 = request.getParameter("database2");
        String values1 = request.getParameter("values1");
        String values2 = request.getParameter("values2");
               
        try{   	
        	QuandlSource quandlSource1 = new QuandlSource();
        	quandlSource1.setParameters(database1, values1);
        	QuandlSource quandlSource2 = new QuandlSource();
        	quandlSource2.setParameters(database2, values2);
        	
        	System.out.println(quandlSource1.getDataBase());
        	System.out.println(quandlSource2.getDataBase());
        	
        	
        	String json = jsonBuilder.getSources(quandlSource1, quandlSource2).toJsonString();
        	
        	response.getWriter().append(pretty ? format.format(json): json);
        	
        	System.out.println(quandlSource2.getName());
        	
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
