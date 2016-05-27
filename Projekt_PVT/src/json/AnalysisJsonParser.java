package json;

import analysis.Analysis;

public class AnalysisJsonParser implements JsonString {

	private final Analysis analysis;

	public AnalysisJsonParser(Analysis analysis) {
		this.analysis = analysis;
	}

	@Override
	public String toJsonString() {
		return "{"
				+ "\"title\":" + "\""+ analysis.getTitle() + "\","
				+ "\"database1\":" + "\"" + analysis.getFirstDatabaseWithSource().getDatabase().link() + "\","
				+ "\"datasource1\":" + "\"" + analysis.getFirstDatabaseWithSource().getSourceId() + "\","
				+ "\"database2\":" + "\"" + analysis.getSecondDatabaseAndSource().getDatabase().link() + "\","
				+ "\"datasource2\":" + "\"" + analysis.getSecondDatabaseAndSource().getSourceId() + "\","
				+ "\"resolution\":" + "\"" + analysis.getResolution() + "\","
				+ "\"startDate\":" + "\"" + analysis.getDateRange().getStartDate() + "\","
				+ "\"endDate\":" + "\"" + analysis.getDateRange().getEndDate() + "\","
				+ "\"comment\":" + "\"" + analysis.getComment() + "\""
				+ "}".replaceAll("\"","\\\"").replace("'", "\\'");
	}
	
	

}
