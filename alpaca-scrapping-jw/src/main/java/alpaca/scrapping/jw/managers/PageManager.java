package alpaca.scrapping.jw.managers;

import org.openqa.selenium.WebDriver;

import alpaca.scrapping.jw.pageobjects.ResultsPage;

public class PageManager {
	private WebDriver driver;
	private ResultsPage resultsPage;

	public PageManager(WebDriver driver) {
		this.driver = driver;
	}

	public ResultsPage getResultsPage() {
		return (resultsPage == null) ? resultsPage = new ResultsPage(driver) : resultsPage;
	}

}
