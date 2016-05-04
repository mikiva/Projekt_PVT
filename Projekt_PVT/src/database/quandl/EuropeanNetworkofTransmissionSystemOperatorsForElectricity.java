package database.quandl;

import java.util.HashMap;
import java.util.Map;

import compare.DataSource;
import database.Database;

public class EuropeanNetworkofTransmissionSystemOperatorsForElectricity implements Database {
	private final String databaseCode;
	private final static Map<String, SourceProperties> DATA_SET = new HashMap<>();
	private final static String UNIT = "Gigawatt Hours (GWh)";

	public EuropeanNetworkofTransmissionSystemOperatorsForElectricity() {
		this.databaseCode = "ENTSOE";
	}

	@Override
	public String link() {
		return databaseCode;
	}

	@Override
	public String[][] dataSet() {
		return DATA_SET.entrySet().stream()
				.map(entry -> new String[] { entry.getKey(), entry.getValue().name() })
				.toArray(String[][]::new);
	}

	@Override
	public DataSource getSource(String id) {
		if (!DATA_SET.containsKey(id))
			throw new IllegalArgumentException("Database doesn't have source: " + id);

		return QuandlSource.createFrom(this.databaseCode, id, DATA_SET.get(id));
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((databaseCode == null) ? 0 : databaseCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EuropeanNetworkofTransmissionSystemOperatorsForElectricity other = (EuropeanNetworkofTransmissionSystemOperatorsForElectricity) obj;
		if (databaseCode == null) {
			if (other.databaseCode != null)
				return false;
		} else if (!databaseCode.equals(other.databaseCode))
			return false;
		return true;
	}
	
	

}
