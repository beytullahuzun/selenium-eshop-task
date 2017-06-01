package task.selenium.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static task.selenium.config.TestProperties.DEFAULT_WAIT_TIMEOUT;
import static task.selenium.utils.SeleniumUtils.assertAllElementsContainString;
import static task.selenium.utils.SeleniumUtils.waitUntil;

public class ResultsPage extends BasePage {

    @FindBy(xpath = "//*[contains(@class,'breadcrumb')]//*[@class='navigation_page' and text()='Search']")
    private WebElement breadCrumbCurrent;

    @FindBy(className = "page-heading")
    private WebElement searchHeading;

    @FindBy(id = "best-sellers_block_right")
    private WebElement bestSellersBlock;

    @FindBy(css = ".product_list .product-container .product-name")
    private List<WebElement> productNames;

    @FindBy(className = "alert-warning")
    private WebElement noResultsWarning;
    
    @FindBy(className = "heading-counter")
    private WebElement resultsCount;

    ResultsPage() {
        PageFactory.initElements(driver, this);
        waitForElements();
    }

    private void waitForElements() {
        waitUntil(visibilityOf(breadCrumbCurrent));
        waitUntil(visibilityOf(searchHeading));
        waitUntil(visibilityOf(bestSellersBlock));
        waitUntil(visibilityOf(resultsCount));
    }

    public void assertAllResultsContainSearchTerm(String searchTerm) {
        assertAllElementsContainString(productNames, searchTerm);
    }

    public void assertNoResultsReturnedForSearchTerm(String searchTerm) {
        waitUntil(visibilityOf(noResultsWarning));
        String warningText = noResultsWarning.getText();
        assertThat(warningText, containsString(searchTerm));

        driver.manage().timeouts().implicitlyWait(2, SECONDS);
        assertThat(productNames.size(), equalTo(0));
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIMEOUT, SECONDS);
    }

    public void assertNoResultsReturnedForSearchTerm() {
        waitUntil(visibilityOf(noResultsWarning));
        String warningText = noResultsWarning.getText();
        String count = resultsCount.getText().replaceAll("\\D+", "");
        assertThat(warningText, containsString("No results were found for your search"));
        assertThat(count, equalTo("0"));
        
        driver.manage().timeouts().implicitlyWait(2, SECONDS);
        assertThat(productNames.size(), equalTo(0));
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIMEOUT, SECONDS);
    }

}