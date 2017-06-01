package task.selenium.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static task.selenium.utils.SeleniumUtils.assertAllElementsContainString;
import static task.selenium.utils.SeleniumUtils.waitUntil;

public class MainPage extends BasePage {

    @FindBy(css = ".ac_results li")
    private List<WebElement> suggestions;

    public MainPage() {
        PageFactory.initElements(driver, this);
    }

    public void assertSuggestionsAreDisplayed(String searchTerm) {
        assertAllElementsContainString(suggestions, searchTerm);
    }

    public ProductPage chooseFirstSuggestion() {
        WebElement firstElement = suggestions.get(0);
        waitUntil(elementToBeClickable(firstElement));
        firstElement.click();
        return new ProductPage();
    }
}
