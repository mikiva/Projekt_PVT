package database.quandl;

public class SourceProperties {

	private final String unit;
	private final String name;

	public SourceProperties(String name, String unit) {
		this.name = name;
		this.unit = unit;
	}
	
	public String name() {
		return name;
	}
	
	public String unit() {
		return unit;
	}
	
}
