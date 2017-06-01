package task.selenium.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import task.selenium.driver.DriverManager;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static task.selenium.utils.SeleniumUtils.*;

public class BasePage {

    WebDriver driver;

    @FindBy(id = "header")
    WebElement header;

    @FindBy(id = "block_top_menu")
    WebElement topMenu;

    @FindBy(id = "header_logo")
    WebElement logo;

    @FindBy(id = "search_query_top")
    WebElement searchBar;

    @FindBy(name = "submit_search")
    WebElement searchButton;

    @FindBy(className = "shopping_cart")
    WebElement shoppingDCart;

    @FindBy(id = "footer")
    WebElement footer;

    BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
        waitForPageToBeLoaded();
        waitForBaseElements();
    }

    private void waitForBaseElements() {
        waitUntil(visibilityOf(header));
        waitUntil(visibilityOf(topMenu));
        waitUntil(visibilityOf(logo));
        waitUntil(visibilityOf(searchBar));
        waitUntil(visibilityOf(shoppingDCart));
        waitUntil(visibilityOf(footer));
    }

    public void enterSearchTerm(String searchTerm) {
        waitUntil(visibilityOf(searchBar));
        searchBar.sendKeys(searchTerm);
    }

    public ResultsPage pressSearchButton() {
        waitUntil(elementToBeClickable(searchButton));
        searchButton.click();
        return new ResultsPage();
    }

}
