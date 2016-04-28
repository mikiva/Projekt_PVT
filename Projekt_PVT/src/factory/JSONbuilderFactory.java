package factory;

import java.util.Arrays;

import compare.DataSourceComparator;
import compare.JSONbuilder;
import compare.SingleDataSource;
import domain.DataSource;

public class JSONbuilderFactory {


	private static String[] sources = new String[] {"gold", "spectators"}; 


	public JSONbuilderFactory(){



	}

	public JSONbuilder getSources (String... strSource){



		DataSource[] src = Arrays.stream(strSource).map(DataSourceFactory::get).toArray(DataSource[]::new);



		if (strSource.length == 2)
			return new DataSourceComparator(src[0], src[1]);
		
		else if (strSource.length == 1)
			return new SingleDataSource(src[0]);
		
		else
			throw new FactoryException("Wrong parameters");

	}

	
	public JSONbuilder getSources (DataSource... dataSource){
		return new DataSourceComparator(dataSource[0], dataSource[1]);
	}
	

	public static void main(String[] args) {

		JSONbuilderFactory json = new JSONbuilderFactory();

		json.getSources(sources);

	}

}
