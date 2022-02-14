package alpaca.scrapping.jw.engine;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import alpaca.scrapping.jw.managers.PageManager;
import alpaca.scrapping.jw.managers.WebDriverManager;
import alpaca.scrapping.jw.objects.Movie;
import alpaca.scrapping.jw.utilities.api.ResponseRetrival;
import alpaca.scrapping.jw.utilities.api.RestHelper;
import alpaca.scrapping.jw.utilities.api.Utilitarian;
import alpaca.scrapping.jw.utilities.constants.Endpoints;

public class CoreEngineDeux extends CoreEngine {

	private WebDriverManager webDriverManager;
	private WebDriver driver;
	private PageManager pageManager;
	private List<String> movieList;
	private RestHelper helper;
	private List<Movie> movieDatabase;
	public static JsonArray genres;

	public CoreEngineDeux() {
		this.webDriverManager = new WebDriverManager();
		this.driver = webDriverManager.getDriver();
		this.pageManager = new PageManager(driver);
		this.movieList = new ArrayList<String>();
		helper = new RestHelper();
		this.movieDatabase = new ArrayList<Movie>();
		this.getGenres();
	}

	private void getGenres() {
		genres = helper.submitGETRequestReturningArray(Endpoints.GENRES);
	}

	private void peruseThroughGridResults() {
		List<WebElement> gridResults = pageManager.getResultsPage().getGridResults();
		String movieLink;
		for (WebElement result : gridResults) {
			movieLink = result.getAttribute("href");
			this.movieList.add(Utilitarian.getmovieNamefromJW(movieLink));
		}
	}

	private void sub() {
		String fileContent = Utilitarian.getFileContent("bodies/search.json");
		JsonObject results;
		for (String movieName : movieList) {

			String body = Utilitarian.getUpdateSearchBody(movieName, fileContent);

			String endpoint = Endpoints.TITLE_SEARCH;
			results = helper.submitPOSTRequest(endpoint, body);
			String movieID = Utilitarian.getmovieID(results);

			endpoint = Endpoints.ID_SEARCH;
			endpoint = Utilitarian.updateEndpointWithID(endpoint, movieID);
			JsonObject object = helper.submitGETRequest(endpoint);

			Movie movie = new Movie();
			movie.setMovieName(ResponseRetrival.getMovieTitle(object));
			movie.setAgeRating(ResponseRetrival.getMovieAgeCertification(object));
			movie.setCountryOfOrigin(ResponseRetrival.getMovieCountry(object));
			movie.setReleaseDate(ResponseRetrival.getMovieReleaseDate(object));
			movie.setReleaseYear(ResponseRetrival.getMovieReleaseYear(object));
			movie.setRuntime(ResponseRetrival.getMovieRuntime(object));
			movie.setTopBillActor(ResponseRetrival.getTopBilledActor(object));
			movie.setImdbRating(ResponseRetrival.getIMDBRating(object));
			movie.setImdbNumberOfReviews(ResponseRetrival.getIMDBVotes(object));
			movie.setDirector(ResponseRetrival.getDirector(object));
			movie.setGenre(ResponseRetrival.getGenres(object));
			movie.setLanguage(ResponseRetrival.getMovieOriginalLanguageTitle(object));

			movieDatabase.add(movie);
			this.displayResults(movie);

			// break;
		}
	}

	public void scrapeData(String url) {
		this.navigate(url);
		this.scrollTillAllResultsAreLoaded();
		this.peruseThroughGridResults();
		this.closeSession();
		this.sub();
	}
}