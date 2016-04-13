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
import domain.DataSource;
import factory.DataSourceFactory;

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
        Boolean pretty = Boolean.valueOf(request.getParameter("pretty"));
        JsonFormatter format = new JsonFormatter();
        String datasource1 = request.getParameter("datasource1");
        String datasource2 = request.getParameter("datasource2");
        
        try (PrintWriter writer = response.getWriter()) {
        	DataSource source1 = DataSourceFactory.get(datasource1);
        	DataSource source2 = DataSourceFactory.get(datasource2);
            DataSourceComparator gt = new DataSourceComparator(source1, source2);
            writer.append((pretty ? format.format(gt.getData()) : gt.getData()));
		} catch (RuntimeException e) {
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
