package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveAnalyzeServlet
 */
@WebServlet("/SaveAnalyzeServlet")
public class SaveAnalyzeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveAnalyzeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String resolution = request.getParameter("resolution");
		String dateBefore = request.getParameter("dateBefore");
		String dateAfter = request.getParameter("dateAfter");
		String databaseOne = request.getParameter("databaseOne");
		String datasetOne = request.getParameter("datasetOne");
		String dataBaseTwo = request.getParameter("databaseTwo");
		String datasetTwo = request.getParameter("datasetTwo");
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
