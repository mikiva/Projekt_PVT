package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import analysis.Title;
import analysis.database.AnalysisTable;
import analysis.database.SqlDatabase;

/**
 * Servlet implementation class DeleteAnalysisServlet
 */
@WebServlet("/DeleteAnalysisServlet")
public class DeleteAnalysisServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private SqlDatabase db;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteAnalysisServlet() {
	}
	
	public DeleteAnalysisServlet(SqlDatabase db) {
		super();
		this.db = db;
	}
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.db = new SqlDatabase(AnalysisTable.getInstance());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		try {
			Title title = new Title(request.getParameter("title"));			

			if (!db.getSavedTitles().contains(title)) {
				response.getWriter().append("Analysis with that title does not exists");
			} else {
				db = db.deleteData(title);
				response.getWriter().append("Analysis " + title.toString() + " deleted");
			}
		} catch(NullPointerException e) {
			response.getWriter().append("Title cannot be empty!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
