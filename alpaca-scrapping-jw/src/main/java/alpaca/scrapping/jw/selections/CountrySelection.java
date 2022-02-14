package alpaca.scrapping.jw.selections;

public enum CountrySelection {

	INDIA("in");

	private String value;

	private CountrySelection(String value) {
		this.value = value;
	}

	public String getCountrySelection() {
		return this.value;
	}
}
