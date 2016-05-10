package database.quandl;

import java.util.HashMap;
import java.util.Map;

public class EuropeanNetworkofTransmissionSystemOperatorsForElectricity extends QuandlDatabase {
	private final static String DATABASE_CODE = "ENTSOE";
	private final static Map<String, SourceProperties> DATA_SET = new HashMap<>();
	private final static String UNIT = "Gigawatt Hours (GWh)";

	public EuropeanNetworkofTransmissionSystemOperatorsForElectricity() {
		super(DATABASE_CODE, DATA_SET);
	}

	static {
		DATA_SET.put("SE_PROD", new SourceProperties("Electricity Production - Sweden", UNIT));
		DATA_SET.put("FR_PROD", new SourceProperties("Electricity Production - France", UNIT));
		DATA_SET.put("BG_PROD", new SourceProperties("Electricity Production - Bulgaria", UNIT));
		DATA_SET.put("FI_PROD", new SourceProperties("Electricity-Production - Finland", UNIT));
		DATA_SET.put("DE_PROD", new SourceProperties("Electricity Production - Germany", UNIT));
		DATA_SET.put("BE_PROD", new SourceProperties("Electricity Production - Belgium", UNIT));
		DATA_SET.put("NL_PROD", new SourceProperties("Electricity Production - The Netherlands", UNIT));
		DATA_SET.put("ES_PROD", new SourceProperties("Electricity Production - Spain", UNIT));
		DATA_SET.put("SE_CONS", new SourceProperties("Electricity Consumption - Sweden", UNIT));
		DATA_SET.put("FR_CONS", new SourceProperties("Electricity Consumption - France", UNIT));
		DATA_SET.put("BG_CONS", new SourceProperties("Electricity Consumption - Bulgaria", UNIT));
		DATA_SET.put("FI_CONS", new SourceProperties("Electricity Consumption - Finland", UNIT));
		DATA_SET.put("DE_CONS", new SourceProperties("Electricity Consumption - Germany", UNIT));
		DATA_SET.put("BE_CONS", new SourceProperties("Electricity Consumption - Belgium", UNIT));
		DATA_SET.put("NL_CONS", new SourceProperties("Electricity Consumption - The Netherlands", UNIT));
		DATA_SET.put("ES_CONS", new SourceProperties("Electricity Consumption - Spain", UNIT));
	}
	
	@Override
	public String toString() {
		return "EuropeanNetworkofTransmissionSystemOperatorsForElectricity";
	}
	

}
