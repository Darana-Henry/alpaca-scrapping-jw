package alpaca.scrapping.jw.selections;

public enum ProviderSelection {

	HOTSTAR("hst"), AMAZONPRIME("prv"), NETFLIX("nfx");

	private String value;

	private ProviderSelection(String value) {
		this.value = value;
	}

	public String getProviderSelection() {
		return this.value;
	}
}
