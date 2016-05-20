package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import json.AvailableDatabases;

/**
 * Servlet implementation class GetAvailableAnalyzeServlet
 */
@WebServlet("/GetAvailableAnalyzeServlet")
public class GetAvailableAnalyzeServlet extends HttpServlet {
	
	String json = "{"
			+ "\"headings\":["
			+ "{\"heading\": \"random text\"}, "
			+ "{\"heading\": \"hejhej\"}, "
			+ "{\"heading\": \"hejdå\"}, "
			+ "{\"heading\": \"hej svejs lingonfejs\"}, "
			+ "{\"heading\": \"magan\"}, "
			+ "{\"heading\": \"dansa och ha kul\"}, "
			+ "{\"heading\": \"jultomten finns\"}]"
			+ "}";
			
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAvailableAnalyzeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
