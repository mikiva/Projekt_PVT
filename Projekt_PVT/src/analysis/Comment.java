package analysis;

public class Comment {

	private final String text;

	public Comment(String text) {
		this.text = text.replaceAll("\"","\\\"")!= null? text : "";
	}
	
	@Override
	public String toString() {
		return text;
	}

}
