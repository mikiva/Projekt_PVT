package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String resolution = request.getParameter("resolution");
		String dateBefore = request.getParameter("dateBefore");
		String dateAfter = request.getParameter("dateAfter");
		String databaseOne = request.getParameter("databaseOne");
		String datasetOne = request.getParameter("datasetOne");
		String dataBaseTwo = request.getParameter("databaseTwo");
		String datasetTwo = request.getParameter("datasetTwo");
		
		if (!check.isAlphaNumeric(title))
			errorMessage.append("Title can only contains number and alphabet");
		
		if (!check.validResulution(resolution))
			errorMessage.append("Wrong resulution format");
		if (!check.validDate(dateBefore) || !check.validDate(dateAfter))
			errorMessage.append("Wrong date format");
		if (!check.databaseExist(databaseOne) || !check.databaseExist(dataBaseTwo))
			errorMessage.append("Wrong database format");
		if (!check.dataSetExist(databaseOne, datasetOne) || !check.dataSetExist(dataBaseTwo, datasetTwo))
			errorMessage.append("Wrong dateset format");
		
		if (errorMessage.length() == 0)	{
			System.out.println("ingen fel. sql query här sen");
			response.getWriter().append(errorMessage.toString());
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
