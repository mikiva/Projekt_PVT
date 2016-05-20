package sql;

import analysis.Analysis;
import analysis.AnalysisTitle;

public interface AnalysisDatabase {

	boolean saveData(Analysis a);

	Analysis getSavedData(AnalysisTitle title);

}