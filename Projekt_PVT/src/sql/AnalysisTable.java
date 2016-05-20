package sql;

import analysis.Analysis;
import analysis.AnalysisTitle;

public interface AnalysisTable {

	boolean saveData(Analysis a);

	Analysis getSavedData(AnalysisTitle title);

}