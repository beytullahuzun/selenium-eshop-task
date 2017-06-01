package task.selenium.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static task.selenium.utils.SeleniumUtils.waitUntil;

public class ProductPage extends BasePage {

    @FindBy(id = "bigpic")
    private WebElement productImage;

    @FindBy(id = "buy_block")
    private WebElement buyBlock;

    @FindBy(css = "[itemprop=name]")
    private WebElement productName;

    @FindBy(className = "page-product-box")
    private WebElement productDescription;

    ProductPage() {
        PageFactory.initElements(driver, this);
        waitForElements();
    }

    private void waitForElements() {
        waitUntil(visibilityOf(productImage));
        waitUntil(visibilityOf(buyBlock));
        waitUntil(visibilityOf(productName));
        waitUntil(visibilityOf(productDescription));
    }

    public void assertProductNameContains(String searchTerm) {
        assertThat(productName.getText().toLowerCase(), containsString(searchTerm));
    }
}
