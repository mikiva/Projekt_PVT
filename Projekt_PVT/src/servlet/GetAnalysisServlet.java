package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import analysis.Analysis;
import analysis.Title;
import analysis.database.AnalysisTable;
import analysis.database.SqlDatabase;
import analysis.database.TableException;
import json.AnalysisJsonParser;
import servlet.helper.CheckIfAnalyzeDataIsValid;

/**
 * Servlet implementation class GetAnalyzeServlet
 */
@WebServlet("/GetAnalysisServlet")
public class GetAnalysisServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CheckIfAnalyzeDataIsValid checker;
	private SqlDatabase db;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAnalysisServlet() {
		super();
	}
	
	GetAnalysisServlet(SqlDatabase db) {
		this();
		this.db = db;
	}

	@Override
	public void init() throws ServletException {
		super.init();
		checker = new CheckIfAnalyzeDataIsValid();
		this.db = new SqlDatabase(AnalysisTable.getInstance());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		String responseMessage = "";
		String titleParam = request.getParameter("title");

		if (!checker.isAlphaNumeric(titleParam)) {
			responseMessage = "Title format is incorrect";
		} else {
			try {
				Title title = new Title(titleParam);
				Analysis retrievedAnalysis = db.getSavedData(title);
				responseMessage = new AnalysisJsonParser(retrievedAnalysis).toJsonString();
			} catch (NullPointerException e) {
				responseMessage = "Illegal parameter(s) from database table";
			}
		}
		response.getWriter().append(responseMessage);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
