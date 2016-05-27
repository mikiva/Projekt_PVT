package database.quandl;

import java.util.HashMap;
import java.util.Map;

public class IMFCrossCountryMacroeconomicStatistics extends QuandlDatabase {
	private static final String DATABASE_CODE = "ODA";
	private final static Map<String, SourceProperties> DATASET = new HashMap<>();

	static {
		DATASET.put("PBANSOP_USD", new SourceProperties("Bananas", "US dollar per metric ton"));
		DATASET.put("SWE_LE", new SourceProperties("Sweden-Employment", "Millions"));
		DATASET.put("SWE_LP", new SourceProperties("Sweden Population", "Millions"));
	}

	public IMFCrossCountryMacroeconomicStatistics() {
		super(DATABASE_CODE, DATASET);
	}

	@Override
	public String toString() {
		return "IMFCrossCountryMacroeconomicStatistics";
	}
	
}
