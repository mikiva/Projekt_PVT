package servlet.helper;

import database.DatabaseFactory;

public class Main {

	public static void main(String[] args) {
		CheckIfAnalyzeDataIsValid c = new CheckIfAnalyzeDataIsValid();
		System.out.println(c.databaseExist("ODA"));
		System.out.println(c.databaseExist("Oadasdas"));
		DatabaseFactory ok = new DatabaseFactory();
		System.out.println(ok.get("okej"));
		
	}

}
