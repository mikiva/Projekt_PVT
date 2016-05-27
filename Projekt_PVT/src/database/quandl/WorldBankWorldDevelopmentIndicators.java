package database.quandl;

import java.util.HashMap;
import java.util.Map;

public class WorldBankWorldDevelopmentIndicators extends QuandlDatabase {
	
	private final static String DATABASE_CODE = "WWDI";
	private final static Map<String, SourceProperties> DATASET = new HashMap<>();
	
	static {
		DATASET.put("SWE_NY_GDP_MKTP_KD_ZG", new SourceProperties("GDP growth Sweden", "Annual %"));
	}
	
	public WorldBankWorldDevelopmentIndicators() {
		super(DATABASE_CODE, DATASET);
	}

	@Override
	public String toString() {
		return "WorldBankWorldDevelopmentIndicators";
	}
	
}
