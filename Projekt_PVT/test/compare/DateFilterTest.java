package compare;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class DateFilterTest {

	@Test
	public void filteringDataFromEmptySourceShouldReturnEmptySource() throws Exception {
		DataSource emptySource = mock(DataSource.class);	
		assertTrue(new DateFilter().getFilteredData(emptySource, "2014-01-01", "2015-01-01").getData().isEmpty());
	}
	
	@Test
	public void returnsNullWhenSourceIsNull() throws Exception {
		assertNull(new DateFilter().getFilteredData(null, null, null));
	}
	
	@Test(expected=NullPointerException.class)
	public void throwsExceptionIfDateStringsAreInvalid() throws Exception {
		new DateFilter().getFilteredData(mock(DataSource.class), null, null);
	}
	
	@Test
	public void filterDataInSource() throws Exception {
		DataSource source = mock(DataSource.class);
		Map<LocalDate, Double> map = new HashMap<>();
		
		map.put(LocalDate.of(2014, 05, 1), 5.0);
		map.put(LocalDate.of(2014, 11, 21), 5.1);
		map.put(LocalDate.of(2018, 01, 10), 5.2);
		map.put(LocalDate.of(2014, 12, 30), 999999.0);
		map.put(LocalDate.of(1887, 11, 21), 0.9);
		
		when(source.getData()).thenReturn(map);
		
		DataSource expectedSource = mock(DataSource.class);
		Map<LocalDate, Double> expectedMap = new HashMap<>();
		
		expectedMap.put(LocalDate.of(2014, 05, 1), 5.0);
		expectedMap.put(LocalDate.of(2014, 11, 21), 5.1);
		expectedMap.put(LocalDate.of(2014, 12, 30), 999999.0);
		
		when(expectedSource.getData()).thenReturn(expectedMap);
		
		assertEquals(expectedSource.getData(), new DateFilter().getFilteredData(source, "2014-01-01", "2015-01-01").getData());
		
	}
	
}
