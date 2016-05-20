package database;

import java.util.HashMap;
import java.util.Map;

import database.misc.MiscDatabase;
import database.quandl.EuropeanNetworkofTransmissionSystemOperatorsForElectricity;
import database.quandl.IMFCrossCountryMacroeconomicStatistics;
import database.quandl.WorldBankWorldDevelopmentIndicators;

public class DatabaseFactory {

	private final static Map<String, Database> DATABASES = new HashMap<>();

	static {
		DATABASES.put("ENTSOE", new EuropeanNetworkofTransmissionSystemOperatorsForElectricity());
		DATABASES.put("ODA", new IMFCrossCountryMacroeconomicStatistics());
		DATABASES.put("WWDI", new WorldBankWorldDevelopmentIndicators());
		DATABASES.put("misc", new MiscDatabase());
	}

	public static Database get(String id) {
		return DATABASES.getOrDefault(id, Database.EMPTY_DATABASE);
	}

	public static Database[] all() {
		return DATABASES.values().toArray(new Database[0]);
	}
}
