package database;

import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class UrlFetcherTest {

	private URL createMockURL() throws MalformedURLException {
		return new File("src/static.json").toURI().toURL();
	}
	
	@Test
	public void checkIfRetrievedContentIsAsExpected() throws Exception {
		UrlFetcher fetcher = new UrlFetcher(createMockURL());
		assertEquals(EXPECTED, fetcher.getContent());
	}
	
	private final static String EXPECTED = "{   "
			+ " \"dataset\": {        "
			+ "\"id\": 1,        "
			+ "\"name\": \"Number of something\",        "
			+ "\"description\": \"\",       "
			+ " \"column_names\": [           "
			+ " \"Date\",           "
			+ " \"Something\"        "
			+ "],        "
			+ "\"data\": [            "
				+ "[                "
				+ "\"2014-12-12\",               "
				+ " 1.0           "
			+ " ],            "
			+ "[                "
				+ "\"2014-09-29\","
				+ "                6969.0            "
				+ "],            "
			+ "[                "
				+ "\"2014-08-04\",            "
				+ "    255.0            "
			+ "],           "
			+ " [                "
				+ "\"2014-05-26\",                "
				+ "30.0            "
			+ "],            "
			+ "[                "
				+ "\"2014-05-08\",                "
				+ "900.0            "
			+ "],            "
			+ "[                "
				+ "\"2014-04-28\",                "
				+ "12.0            "
			+ "],            "
			+ "[                "
				+ "\"2014-04-17\",                "
				+ "99.0            "
			+ "],            "
			+ "[                "
				+ "\"2014-01-01\",                "
				+ "5480.0            "
			+ "]        "
			+ "]    "
		+ "}"
	+ "}";

}
