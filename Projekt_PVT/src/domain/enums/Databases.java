package domain.enums;

public enum Databases {

	IMFCrossCountryMacroeconomicStatistics {
		@Override
		public String link() {
			return "ODA";
		}
		@Override
		public String[][] getDataSets() {
			String[][] dataSetsInfo = new String[][] {
				{"PBANSOP_USD", "Bananas"},
				{"SWE_LE"	  , "Sweden-Employment"},
				{"SWE_LP"     , "Sweden Population"},
			};
			return dataSetsInfo;
		}
	},
	WorldBankWorldDevelopmentIndicators {
		@Override
		public String link() {
			return "WWDI";
		}
		@Override
		public String[][] getDataSets() {
			String [][] dataSetsInfo = new String[][] {
				{"SWE_NY_GDP_MKTP_KD_ZG", "GDP growth Sweden"}
			};
			return dataSetsInfo;
		}
	},
	EuropeanNetworkofTransmissionSystemOperatorsForElectricity {
		@Override
		public String link() {
			return "ENTSOE";
		}
		@Override
		public String[][] getDataSets() {
			String [][] dataSetsInfo = new String[][] {
				{"SE_PROD", "Electricity Production - Sweden"},
				{"FR_PROD", "Electricity Production - France"},
				{"BG_PROD", "Electricity Production - Bulgaria"},
				{"FI_PROD", "Electricity-Production - Finland"},
				{"DE_PROD", "Electricity Production - Germany"},
				{"BE_PROD", "Electricity Production - Belgium"},
				{"NL_PROD", "Electricity Production - The Netherlands"},
				{"ES_PROD", "Electricity Production - Spain"},
				{"SE_CONS", "Electricity Consumption - Sweden"},
				{"FR_CONS", "Electricity Consumption - France"},
				{"BG_CONS", "Electricity Consumption - Bulgaria"},
				{"FI_CONS", "Electricity Consumption - Finland"},
				{"DE_CONS", "Electricity Consumption - Germany"},
				{"BE_CONS", "Electricity Consumption - Belgium"},
				{"NL_CONS", "Electricity Consumption - The Netherlands"},
				{"ES_CONS", "Electricity Consumption - Spain"}
			};
			return dataSetsInfo;
		}
	};

	public abstract String link();
	public abstract String[][] getDataSets();

	public String addSpaces() {
		return toString().replaceAll(String.format("%s|%s|%s",
					"(?<=[A-Z])(?=[A-Z][a-z])",
					"(?<=[^A-Z])(?=[A-Z])",
					"(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
	}

}
