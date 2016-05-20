package database;

import compare.DataSource;

public class NullDatabase implements Database {

	@Override
	public String link() {
		return "";
	}

	@Override
	public String[][] dataSet() {
		return new String[][] {};
	}

	@Override
	public DataSource getSource(String id) {
		return null;
	}

}
