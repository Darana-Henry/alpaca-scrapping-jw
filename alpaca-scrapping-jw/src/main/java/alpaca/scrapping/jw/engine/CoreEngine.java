package alpaca.scrapping.jw.engine;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import alpaca.scrapping.jw.managers.PageManager;
import alpaca.scrapping.jw.managers.WebDriverManager;
import alpaca.scrapping.jw.objects.Movie;
import alpaca.scrapping.jw.objects.MovieListWithParsedTag;

public class CoreEngine {

	private WebDriverManager webDriverManager;
	private WebDriver driver;
	private PageManager pageManager;
	private List<String> movieList;
	private List<Movie> movieDatabase;
	private List<MovieListWithParsedTag> jwMovieLinksList;

	public CoreEngine() {
		this.webDriverManager = new WebDriverManager();
		this.driver = webDriverManager.getDriver();
		this.pageManager = new PageManager(driver);
		this.movieList = new ArrayList<String>();
		this.movieDatabase = new ArrayList<Movie>();
		this.jwMovieLinksList = new ArrayList<MovieListWithParsedTag>();
	}

	protected void navigate(String url) {
		this.driver.get(url);
	}

	protected void scrollTillEndOfPage() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	protected void scrollToView(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	protected void scrollTillAllResultsAreLoaded() {
		boolean haveAllResultsLoaded = false;
		while (!haveAllResultsLoaded) {
			this.scrollTillEndOfPage();
			haveAllResultsLoaded = pageManager.getResultsPage().isEndOfPage();
		}
	}

	private void peruseThroughGridResults() {
		List<WebElement> gridResults = pageManager.getResultsPage().getGridResults();
		String movieLink;
		for (WebElement result : gridResults) {
			movieLink = result.getAttribute("href");
			this.movieList.add(movieLink);
		}
		for (String movie : movieList) {
			this.navigate(movie);
			MovieListWithParsedTag movieLWPT = new MovieListWithParsedTag();
			movieLWPT.setJwLink(getIMDBLink());
			this.jwMovieLinksList.add(movieLWPT);
			this.populateMovieDatabase(this.getIMDBLink());
		}
	}

	private String getIMDBLink() {
		String IMDBLink;
		String xpathIMDBLink = "(//a[contains(@href,'imdb.com/title')])[2]";
		WebElement element = driver.findElement(By.xpath(xpathIMDBLink));
		this.scrollToView(element);

		IMDBLink = element.getAttribute("href");
		IMDBLink = IMDBLink.replaceAll("pro.", "");

		int indexOfQuestiomMark = IMDBLink.indexOf("/?");
		IMDBLink = IMDBLink.substring(0, indexOfQuestiomMark).trim();

		return element.getAttribute("href");

	}

	private void populateMovieDatabase(String url) {
		// String ageRatingXpath = "";
		// String contentProviderXpath = "";
		String countryOfOriginXpath = "//span[contains(text(),'Country of origin')]//following-sibling::div//a[contains(@href,'country')]";
		String directorXpath = "(//span[contains(text(),'Director')]//following-sibling::div//a[contains(@href,'name')])[1]";
		String genreXpath = "(//span[contains(text(),'Genre')]//following-sibling::div//a[contains(@href,'genre')])[1]";
		String imdbNumberOfReviewsXpath = "(//div[@class='AggregateRatingButton__TotalRatingAmount-sc-1ll29m0-3 jkCVKJ'])[1]";
		String imdbRatingXpath = "(//span[@class='AggregateRatingButton__RatingScore-sc-1ll29m0-1 iTLWoV'])[1]";
		String languageXpath = "//span[contains(text(),'Language')]//following-sibling::div//a[contains(@href,'language')]";
		String movieNameXpath = "//h1[@data-testid='hero-title-block__title']";
		String releaseDateXpath = "//a[contains(text(),'Release date')]//following-sibling::div//a[contains(@href,'release')]";
		// String releaseYearXpath =
		// "(//span[@class='TitleBlockMetaData__ListItemText-sc-12ein40-2 jedhex'])[1]";
		String runtimeXpath = "//span[contains(text(),'Runtime')]//following-sibling::div";
		String topBillActorXpath = "(//a[contains(text(),'Stars')]//following-sibling::div//a[contains(@href,'name')])[1]";

		Movie movie = new Movie();
		this.navigate(getIMDBLink());

		// movie.setAgeRating(this.getProperty(ageRatingXpath));
		// movie.setContentProvider(this.getProperty(contentProviderXpath));
		movie.setCountryOfOrigin(this.getProperty(countryOfOriginXpath));
		movie.setDirector(this.getProperty(directorXpath));
		movie.setGenre(this.getProperty(genreXpath));
		movie.setImdbLink(getIMDBLink());
		movie.setImdbNumberOfReviews(this.getProperty(imdbNumberOfReviewsXpath));
		movie.setImdbRating(this.getProperty(imdbRatingXpath));
		movie.setLanguage(this.getProperty(languageXpath));
		movie.setMovieName(this.getProperty(movieNameXpath));
		movie.setReleaseDate(this.getProperty(releaseDateXpath));
		movie.setRuntime(this.getProperty(runtimeXpath));
		movie.setTopBillActor(this.getProperty(topBillActorXpath));
		movie.setReleaseYear(movie.getReleaseDate().substring((movie.getReleaseDate().toString().indexOf(",", 0) + 1),
				(movie.getReleaseDate().toString().indexOf("(", 0) - 1)).trim());

		movieDatabase.add(movie);
		this.displayResults(movie);
	}

	private String getProperty(String xpath) {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			this.scrollToView(element);
			return element.getText().trim();
		} catch (Exception e) {
			return "";
		}
	}

	protected void displayResults(Movie movie) {
		System.out.println();
		System.out.print(movie.getReleaseYear() + "\t");
		System.out.print(movie.getReleaseDate() + "\t");
		System.out.print(movie.getCountryOfOrigin() + "\t");
		System.out.print(movie.getLanguage() + "\t");
		System.out.print(movie.getMovieName() + "\t");
		System.out.print(movie.getRuntime() + "\t");
		System.out.print(movie.getImdbRating() + "\t");
		System.out.print(movie.getDirector() + "\t");
		System.out.print(movie.getTopBillActor() + "\t");
		System.out.print(movie.getGenre() + "\t");
		System.out.print(movie.getImdbLink() + "\t");

	}

	protected void closeSession() {
		driver.quit();
	}

	public void scrapeData(String url) {

		this.navigate(url);
		this.scrollTillAllResultsAreLoaded();
		this.peruseThroughGridResults();

		this.closeSession();
	}

}
