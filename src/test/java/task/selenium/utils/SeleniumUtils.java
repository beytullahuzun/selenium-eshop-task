package task.selenium.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;
import static task.selenium.config.TestProperties.DEFAULT_WAIT_TIMEOUT;
import static task.selenium.driver.DriverManager.getDriver;

public class SeleniumUtils {

    public static void waitUntil(ExpectedCondition<?> condition) {
        WebDriverWait wait = new WebDriverWait(getDriver(), DEFAULT_WAIT_TIMEOUT);
        wait.until(condition);
    }

    public static void waitForPageToBeLoaded() {
        ExpectedCondition<Boolean> pageIsLoaded = (driver) ->
                ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        waitUntil(pageIsLoaded);
    }

    public static void assertAllElementsContainString(List<WebElement> elements, String searchTerm) {
        waitUntil(visibilityOfAllElements(elements));
        elements.stream()
                .map(WebElement::getText)
                .forEach(s -> assertThat(s.toLowerCase(), containsString(searchTerm)));
    }
}
