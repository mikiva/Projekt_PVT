package database;

import compare.DataSource;

public interface Database {
	
	String link();

	String[][] dataSet();

	DataSource getSource(String id);

}
