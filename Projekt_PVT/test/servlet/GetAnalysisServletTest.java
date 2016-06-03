package servlet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import analysis.Analysis;
import analysis.Comment;
import analysis.DatabaseWithSource;
import analysis.DateRange;
import analysis.Title;
import analysis.database.SqlDatabase;
import compare.Resolution;
import database.Database;

public class GetAnalysisServletTest {

	@Test
	public void getAnalysisFromServlet() throws Exception {
		SqlDatabase mockDB = mock(SqlDatabase.class);
		GetAnalysisServlet servlet = new GetAnalysisServlet(mockDB);
		Set<Title> savedTitles = new HashSet<>();
		savedTitles.add(new Title("test"));

		when(mockDB.getSavedTitles()).thenReturn(savedTitles);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);
		
		servlet.doGet(request, response);
	}
	
	private Analysis createAnalysis() {
		DatabaseWithSource dbws = mock(DatabaseWithSource.class);
		Database db = mock(Database.class);
		when(dbws.getDatabase()).thenReturn(db);
		return new Analysis(
				dbws, 
				dbws, 
				Resolution.DAY, 
				new DateRange("2015-01-01", "2015-02-02"), 
				new Title("text"), 
				new Comment("comment"));
	}

}
