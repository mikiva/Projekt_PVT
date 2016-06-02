package json;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import compare.DataSource;
import compare.Resolution;

public class JsonStringFactoryTest {
	
	private Resolution res = Resolution.DAY;
	
	private DataSource createDataSource() {
		return mock(DataSource.class);
	}

	@Test
	public void getSingleDataSourceJson() throws Exception {
		DataSource source = createDataSource();
		assertEquals(SingleDataSourceJson.class, JsonStringFactory.get(res, source).getClass());
	}
	
	@Test
	public void getComparedDataSourceJson() throws Exception {
		DataSource source = createDataSource();
		assertEquals(ComparedDataSourceJson.class, JsonStringFactory.get(res, source, source).getClass());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void throwsExceptionWhenZeroDataSourcesIsUsed() throws Exception {
		JsonStringFactory.get(res);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void throwsExceptionWhenMoreThanTwoDataSourcesAreUsed() throws Exception {
		DataSource source = createDataSource();
		JsonStringFactory.get(res, source, source, source);
	}
	
	@Test
	public void onlyNonNullDataSourcesAreUsedForEvaluation() throws Exception {
		DataSource source = createDataSource();
		assertEquals(SingleDataSourceJson.class, JsonStringFactory.get(res, source, null).getClass());
		assertEquals(ComparedDataSourceJson.class, JsonStringFactory.get(res, source, null, source).getClass());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void completelyIgnoresNullDataSources() throws Exception {
		JsonStringFactory.get(res, null, null);
	}
	
}
