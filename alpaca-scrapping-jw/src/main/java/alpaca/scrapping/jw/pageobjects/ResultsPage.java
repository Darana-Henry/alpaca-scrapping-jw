package alpaca.scrapping.jw.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ResultsPage {

	WebDriver driver;

	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//h3[contains(text(),'reached the end of the list')]")
	WebElement youHaveReachedTheEndOfTheList;

	@FindBy(how = How.CLASS_NAME, using = "title-list-grid__item--link")
	List<WebElement> gridResults;

	public boolean isEndOfPage() {
		try {
			return youHaveReachedTheEndOfTheList.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public List<WebElement> getGridResults() {
		return this.gridResults;
	}
}
