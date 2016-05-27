package analysis;

public class Comment {

	private final String text;

	public Comment(String text) {
		this.text = text!= null? text.replaceAll("\"","\\\"") : "";
	}
	
	@Override
	public String toString() {
		return text;
	}

}
