package compare;

import java.util.TreeMap;

import com.owlike.genson.Genson;

import domain.DataSource;
import domain.SpectatorsSource;

public class SingleDataSource implements JSONbuilder{

	
	
	private DataSource src;

	public SingleDataSource(DataSource src) {
		this.src = src;
	
		
		
	}

	@Override
	public String getData() {
		
		
	
		return new Genson().serialize(src.getData());
		
		//return "{{2014-05-26: 123.3},{2014-12-21: 152.3},{2014-09-11: 642.4},{2014-02-02: 75.5},{2014-03-29: 435.4},{2014-12-23: 105.5}}";
	}

}
