package factory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import compare.DataSourceComparator;
import compare.SingleDataSource;

public class JSONbuilderFactoryTest {

	private String[] str = new String[] { "gold", "spectators" };

	private JSONbuilderFactory jFac;

	@Before
	public void setUp() {

		jFac = new JSONbuilderFactory();

	}

	@Test
	public void dataSourceComparatorTest() throws Exception {

		assertEquals(DataSourceComparator.class, jFac.getSources(str).getClass());

	}

	@Test
	public void getSingleDataSourceTest() throws Exception {

		assertEquals(SingleDataSource.class, jFac.getSources("gold").getClass());

	}
	
	@Test(expected = FactoryException.class)
	public void getManyDataSourcesTest() throws Exception {
		
	
		
	}
}
