package json;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import compare.DataSource;

public class ComparedDataSourceJsonTest {

	private static final Map<LocalDate, Double> MAP_1 = new HashMap<>();
	private static final Map<LocalDate, Double> MAP_2 = new HashMap<>();
	private static final String EXCPECTED_JSON = "{\"xName\":\"Source 1\",\"yName\":\"Source 2\",\"xUnit\":\"Price per kg\",\"yUnit\":\"Degrees\",\"data\":[{\"x\":5.1,\"y\":0.9,\"date\":\"1887-11-21\"}]}";

	@Test
	public void testGetJsonString() {
		DataSource source1 = mock(DataSource.class);
		DataSource source2 = mock(DataSource.class);

		when(source1.getName()).thenReturn("Source 1");
		when(source1.getUnit()).thenReturn("Price per kg");
		when(source1.getData()).thenReturn(MAP_1);

		when(source2.getName()).thenReturn("Source 2");
		when(source2.getUnit()).thenReturn("Degrees");
		when(source2.getData()).thenReturn(MAP_2);
		

		ComparedDataSourceJson compared = new ComparedDataSourceJson(source1, source2);
		assertEquals(EXCPECTED_JSON, compared.toJsonString());
	}

	static {
		MAP_1.put(LocalDate.of(2, 05, 1), 5.0);
		MAP_1.put(LocalDate.of(1887, 11, 21), 5.1);
		MAP_1.put(LocalDate.of(2018, 01, 10), 5.2);

		MAP_2.put(LocalDate.of(9999, 12, 30), 999999.0);
		MAP_2.put(LocalDate.of(1887, 11, 21), 0.9);
		MAP_2.put(LocalDate.of(2016, 03, 22), -2.0);
	}

}
