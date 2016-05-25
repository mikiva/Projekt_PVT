package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import analysis.Analysis;
import analysis.DatabaseWithSource;
import analysis.DateRange;
import analysis.Title;
import analysis.database.AnalysisTable;
import analysis.database.SqlDatabase;
import compare.Resolution;
import database.DatabaseFactory;
import servlet.helper.CheckIfAnalyzeDataIsValid;

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
		CheckIfAnalyzeDataIsValid check = new CheckIfAnalyzeDataIsValid();
		StringBuffer errorMessage = new StringBuffer();

		
		String title = request.getParameter("title");
		String resolution = request.getParameter("res");
		String dateBefore = request.getParameter("startDate");
		String dateAfter = request.getParameter("endDate");
		String databaseOne = request.getParameter("database1");
		String datasetOne = request.getParameter("value1");
		String dataBaseTwo = request.getParameter("database2");
		String datasetTwo = request.getParameter("value2");
		
		SqlDatabase db = new SqlDatabase(AnalysisTable.getInstance());
		
		
		
		if (!check.isAlphaNumeric(title))
			errorMessage.append("Title can only contains number and alphabet");
		
		if(!db.getSavedTitles().contains(new Title(title)))
			errorMessage.append("Analysis with that title already exists");
		
		if (!check.validResulution(resolution))
			errorMessage.append("Wrong resulution format");
		if (!check.validDate(dateBefore) || !check.validDate(dateAfter))
			errorMessage.append("Wrong date format");
		if (!check.databaseExist(databaseOne) || !check.databaseExist(dataBaseTwo))
			errorMessage.append("Wrong database format");
		if (!check.dataSetExist(databaseOne, datasetOne) || !check.dataSetExist(dataBaseTwo, datasetTwo))
			errorMessage.append("Wrong dateset format");
		
		if (errorMessage.length() == 0)	{
			
			Title analysisTitle = new Title(title);
			Resolution res = Resolution.valueOf(resolution.toUpperCase());
			DateRange dateRange = new DateRange(dateBefore, dateAfter);
			DatabaseWithSource dbWithSource1 = new DatabaseWithSource(DatabaseFactory.get(databaseOne), datasetOne);
			DatabaseWithSource dbWithSource2 = new DatabaseWithSource(DatabaseFactory.get(dataBaseTwo), datasetTwo);
			
			Analysis analysis = new Analysis(dbWithSource1, dbWithSource2, res, dateRange, analysisTitle);
			
			db.saveData(analysis);
			
			response.getWriter().append("Analys " + analysis.getTitle() + " sparad i databas!");
		} else {
			response.getWriter().append(errorMessage.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
