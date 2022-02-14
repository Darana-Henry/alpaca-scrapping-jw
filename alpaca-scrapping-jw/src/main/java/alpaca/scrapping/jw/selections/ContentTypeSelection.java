package alpaca.scrapping.jw.selections;

public enum ContentTypeSelection {

	MOVIE("movies"), SHOW("tv-shows");

	private String value;

	private ContentTypeSelection(String value) {
		this.value = value;
	}

	public String getContentTypeSelection() {
		return this.value;
	}
}
