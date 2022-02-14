package alpaca.scrapping.jw.builder;

import java.util.ArrayList;
import java.util.List;

public class RequestBuilder {

	// private static RequestBuilder requestBuilder = null;
	private String BASE_URL = "https://www.justwatch.com";
	private String country;
	private String contentMovieOrShow;
	private List<String> providers;
	private String releaseYearFrom;
	private String releaseYearUntil;

	// https://www.justwatch.com/in/movies?providers=hst,jio,nfx,prv,voo,zee&release_year_from=2020&release_year_until=2020

	public RequestBuilder() {
		providers = new ArrayList<String>();
	}

	/*
	 * public static RequestBuilder getInstance() { return (requestBuilder == null)
	 * ? new RequestBuilder() : requestBuilder; }
	 * 
	 */

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContentMovieOrShow() {
		return this.contentMovieOrShow;
	}

	public void setContentMovieOrShow(String contentMovieOrShow) {
		this.contentMovieOrShow = contentMovieOrShow;
	}

	public String getProviders() {
		String returnedProviders = "";
		for (String provider : this.providers) {
			returnedProviders += provider;
			returnedProviders += ",";
		}
		return returnedProviders;
	}

	public void setProviders(List<String> providers) {
		this.providers.clear();
		this.providers = providers;
	}

	public String getReleaseYearFrom() {
		return this.releaseYearFrom;
	}

	public void setReleaseYearFrom(String releaseYearFrom) {
		this.releaseYearFrom = releaseYearFrom;
	}

	public String getReleaseYearUntil() {
		return this.releaseYearUntil;
	}

	public void setReleaseYearUntil(String releaseYearUntil) {
		this.releaseYearUntil = releaseYearUntil;
	}

	public String getBaseUrl() {
		return this.BASE_URL;
	}

	public String getRequestEndpoint() {
		String requestEndpoint;
		requestEndpoint = this.getBaseUrl();
		requestEndpoint += "/";
		requestEndpoint += this.getCountry();
		requestEndpoint += "/";
		requestEndpoint += this.getContentMovieOrShow();
		requestEndpoint += "?";
		requestEndpoint += "providers=";
		requestEndpoint += this.getProviders();
		requestEndpoint += "&release_year_from=";
		requestEndpoint += this.getReleaseYearFrom();
		requestEndpoint += "&release_year_until=";
		requestEndpoint += this.getReleaseYearUntil();
		return requestEndpoint;
	}
}
