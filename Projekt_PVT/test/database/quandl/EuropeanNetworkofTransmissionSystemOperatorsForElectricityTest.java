package database.quandl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import database.Database;

public class EuropeanNetworkofTransmissionSystemOperatorsForElectricityTest {

	private String[][] expectedDataset = new String[][] { 
		{ "NL_CONS", "Electricity Consumption - The Netherlands" },
		{ "SE_CONS", "Electricity Consumption - Sweden" }, { "SE_PROD", "Electricity Production - Sweden" },
		{ "ES_CONS", "Electricity Consumption - Spain" }, { "BE_PROD", "Electricity Production - Belgium" },
		{ "FR_PROD", "Electricity Production - France" }, { "FI_CONS", "Electricity Consumption - Finland" },
		{ "NL_PROD", "Electricity Production - The Netherlands" },
		{ "DE_CONS", "Electricity Consumption - Germany" }, { "BG_PROD", "Electricity Production - Bulgaria" },
		{ "DE_PROD", "Electricity Production - Germany" }, { "FI_PROD", "Electricity-Production - Finland" },
		{ "BG_CONS", "Electricity Consumption - Bulgaria" }, { "ES_PROD", "Electricity Production - Spain" },
		{ "BE_CONS", "Electricity Consumption - Belgium" }, { "FR_CONS", "Electricity Consumption - France" } 
	};

	@Test
	public void getLinkToDatabaseCode() throws Exception {
		assertEquals("ENTSOE", new EuropeanNetworkofTransmissionSystemOperatorsForElectricity().link());
	}

	@Test
	public void getDataset() throws Exception {
		assertArrayEquals(expectedDataset, new EuropeanNetworkofTransmissionSystemOperatorsForElectricity().dataSet());
	}

	@Test
	public void getDataSourceFromDatabase() throws Exception {
		Database db = new EuropeanNetworkofTransmissionSystemOperatorsForElectricity();
		assertNotNull(db.getSource("SE_PROD"));
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwsExceptionWhenDataSourceDoesNotExistInDataBase() throws Exception {
		new EuropeanNetworkofTransmissionSystemOperatorsForElectricity().getSource("NEJ");
	}
	
}
