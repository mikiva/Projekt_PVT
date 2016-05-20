package analysis.database;

import java.sql.Connection;

public interface SqlTable {
	
	Connection connectToDatabase();
	
	String name();

}