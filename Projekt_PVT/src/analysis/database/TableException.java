package analysis.database;

public class TableException extends RuntimeException {
	
	private static final long serialVersionUID = 8607255890568602231L;

	public TableException(Exception e) {
		super(e.getLocalizedMessage());
	}

	public TableException(String msg) {
		super(msg);
	}
	
}
