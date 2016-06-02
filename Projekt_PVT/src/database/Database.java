package database;

import compare.DataSource;

public interface Database {
	
	final Database EMPTY_DATABASE = new NullDatabase();

	String link();

	String[][] dataSet();

	DataSource getSource(String id);

}
