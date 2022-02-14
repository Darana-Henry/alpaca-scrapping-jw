package alpaca.scrapping.jw.objects;

public class MovieListWithParsedTag {
	boolean hasDataBeenPulled = false;
	String imdbID;
	String jwLink;

	public boolean isHasDataBeenPulled() {
		return hasDataBeenPulled;
	}

	public void setHasDataBeenPulled(boolean hasDataBeenPulled) {
		this.hasDataBeenPulled = hasDataBeenPulled;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	public String getJwLink() {
		return jwLink;
	}

	public void setJwLink(String jwLink) {
		this.jwLink = jwLink;
	}

}
