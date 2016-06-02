package database;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import database.misc.MiscDatabase;
import database.quandl.EuropeanNetworkofTransmissionSystemOperatorsForElectricity;
import database.quandl.IMFCrossCountryMacroeconomicStatistics;
import database.quandl.WorldBankWorldDevelopmentIndicators;

public class DatabaseFactoryTest {

	@Test
	public void getEuropeanNetworkofTransmissionSystemOperatorsForElectricity() throws Exception {
		assertEquals(EuropeanNetworkofTransmissionSystemOperatorsForElectricity.class, 
				DatabaseFactory.get("ENTSOE").getClass());
	}
	
	@Test
	public void getIMFCrossCountryMacroeconomicStatistics() throws Exception {
		assertEquals(IMFCrossCountryMacroeconomicStatistics.class, 
				DatabaseFactory.get("ODA").getClass());
	}
	
	@Test
	public void returnsNullFactoryWhenIllegalParameterIsUsed() throws Exception {
		assertEquals(NullDatabase.class, DatabaseFactory.get("ILLEGAL").getClass());
		assertEquals(NullDatabase.class, DatabaseFactory.get(null).getClass());
	}
	
	@Test
	public void getAvailableDatabasesAsArray() throws Exception {
		Database[] expectedResult = new Database[] {
				new IMFCrossCountryMacroeconomicStatistics(),
				new EuropeanNetworkofTransmissionSystemOperatorsForElectricity(),
				new MiscDatabase()
		};
		assertArrayEquals(expectedResult, DatabaseFactory.all());
	}

}
