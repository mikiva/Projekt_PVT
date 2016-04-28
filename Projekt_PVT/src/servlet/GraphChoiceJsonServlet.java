package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import compare.DataBasesComparator;

/**
 * Servlet implementation class GraphChoiceJsonServlet
 */
@WebServlet("/GraphChoiceJsonServlet")
public class GraphChoiceJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataBasesComparator dataBasesComparator;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GraphChoiceJsonServlet() {
    	 super();
    	 dataBasesComparator = new DataBasesComparator();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		
		String json = dataBasesComparator.toJsonString();
		
		response.getWriter().append(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
