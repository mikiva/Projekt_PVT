package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import analysis.Title;
import analysis.database.AnalysisTable;
import analysis.database.SqlDatabase;
import json.ListJsonParser;

/**
 * Servlet implementation class GetAvailableAnalyzeServlet
 */
@WebServlet("/GetAvailableAnalyzeServlet")
public class GetAvailableAnalyzeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAvailableAnalyzeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		SqlDatabase db = new SqlDatabase(AnalysisTable.getInstance());
		List<Title> titles = db.getSavedTitles();
		response.getWriter().append(new ListJsonParser<>(titles).toJsonString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
