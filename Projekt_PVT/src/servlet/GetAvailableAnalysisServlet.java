package servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import analysis.Title;
import analysis.database.AnalysisTable;
import analysis.database.SqlDatabase;
import json.CollectionJsonParser;

/**
 * Servlet implementation class GetAvailableAnalyzeServlet
 */
@WebServlet("/GetAvailableAnalysisServlet")
public class GetAvailableAnalysisServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAvailableAnalysisServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setContentType("application/json;charset=UTF-8");
		SqlDatabase db = new SqlDatabase(AnalysisTable.getInstance());
		Set<Title> titles = db.getSavedTitles();
		response.getWriter().append(new CollectionJsonParser<>(titles).toJsonString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
