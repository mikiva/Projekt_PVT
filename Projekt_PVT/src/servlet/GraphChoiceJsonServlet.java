package servlet;

import java.io.IOException;
<<<<<<< HEAD
=======

>>>>>>> develop
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
import compare.DataBasesComparator;
=======
import json.AvailableDatabases;
>>>>>>> develop

/**
 * Servlet implementation class GraphChoiceJsonServlet
 */
@WebServlet("/GraphChoiceJsonServlet")
public class GraphChoiceJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
<<<<<<< HEAD
	private DataBasesComparator dataBasesComparator;
=======
>>>>>>> develop
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GraphChoiceJsonServlet() {
    	 super();
<<<<<<< HEAD
    	 dataBasesComparator = new DataBasesComparator();
=======
>>>>>>> develop
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		
<<<<<<< HEAD
		String json = dataBasesComparator.toJsonString();
=======
		String json = new AvailableDatabases().toJsonString();
>>>>>>> develop
		
		response.getWriter().append(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
