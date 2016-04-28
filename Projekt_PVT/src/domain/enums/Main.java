package domain.enums;

import compare.DataBasesComparator;

public class Main {

	public static void main(String[] args) {
	
		
		//System.out.println(Databases.IMFCrossCountryMacroeconomicStatistics.link());
		//System.out.println(Databases.IMFCrossCountryMacroeconomicStatistics.addSpaces());
		DataBasesComparator d = new DataBasesComparator();
		
		System.out.println(d.toJsonString());
	}

}
