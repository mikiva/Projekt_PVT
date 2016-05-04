package json;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import compare.DataSource;

public class JsonStringFactoryTest {
	
	private DataSource createDataSource() {
		return mock(DataSource.class);
	}

	@Test
	public void getSingleDataSourceJson() throws Exception {
		DataSource source = createDataSource();
		assertEquals(SingleDataSourceJson.class, JsonStringFactory.get(source).getClass());
	}
	
	@Test
	public void getComparedDataSourceJson() throws Exception {
		DataSource source = createDataSource();
		assertEquals(ComparedDataSourceJson.class, JsonStringFactory.get(source, source).getClass());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void throwsExceptionWhenZeroDataSourcesIsUsed() throws Exception {
		JsonStringFactory.get();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void throwsExceptionWhenMoreThanTwoDataSourcesAreUsed() throws Exception {
		DataSource source = createDataSource();
		JsonStringFactory.get(source, source, source);
	}
	
	@Test
	public void onlyNonNullDataSourcesAreUsedForEvaluation() throws Exception {
		DataSource source = createDataSource();
		assertEquals(SingleDataSourceJson.class, JsonStringFactory.get(source, null).getClass());
		assertEquals(ComparedDataSourceJson.class, JsonStringFactory.get(source, null, source).getClass());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void completelyIgnoresNullDataSources() throws Exception {
		JsonStringFactory.get(null, null);
	}
}
