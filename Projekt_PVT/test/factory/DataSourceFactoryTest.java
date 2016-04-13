package factory;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.DataSource;
import domain.FootballGoalsSource;
import domain.GoldPriceSource;
import domain.SpectatorsSource;
import domain.StaticJSONSource;
import domain.TemperatureSource;

public class DataSourceFactoryTest {
	
	@Test
	public void factoryMakesClassesOfTypeDataSource() {
		assertTrue("factory does not make data sources", DataSourceFactory.get("goals") instanceof DataSource);
	}
	
	@Test(expected=FactoryException.class)
	public void shouldThrowExceptionWhenInvalidStringIsUsed() throws Exception {
		DataSourceFactory.get("invalid string");
	}
	
	@Test
	public void makesFootballGoalsSource() throws Exception {
		assertEquals(FootballGoalsSource.class, DataSourceFactory.get("goals").getClass());
	}
	
	@Test
	public void makesTemperatureSource() throws Exception {
		assertEquals(TemperatureSource.class, DataSourceFactory.get("temperature").getClass());		
	}
	
	@Test
	public void makesGoldPriceSource() throws Exception {
		assertEquals(GoldPriceSource.class, DataSourceFactory.get("gold").getClass());
	}
	
	@Test
	public void makesSpectatorsSource() throws Exception {
		assertEquals(SpectatorsSource.class, DataSourceFactory.get("spectators").getClass());
	}

	@Test
	public void makesStaticSource() throws Exception {
		assertEquals(StaticJSONSource.class, DataSourceFactory.get("static").getClass());
	}
}
