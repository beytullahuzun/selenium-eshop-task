package task.selenium.cucumber.stepdefs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import task.selenium.config.TestProperties;
import task.selenium.driver.DriverManager;
import task.selenium.pageobject.MainPage;
import task.selenium.pageobject.ProductPage;
import task.selenium.pageobject.ResultsPage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static task.selenium.config.TestProperties.SCREENSHOT_PATH;

public class MainStepdefs {
    private WebDriver driver = DriverManager.getDriver();
    private MainPage mainPage;
    private ResultsPage resultsPage;
    private ProductPage productPage;

    @Before
    public void setUp() {
        driver = DriverManager.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshotBytes, "image/png");

            final File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(Paths.get(SCREENSHOT_PATH).toString());

            try {
                FileUtils.copyFile(screenshotFile, destFile);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        DriverManager.closeDriver();
    }

    @Given("^client is at main page$")
    public void clientIsAtMainPage() {
        driver.get(TestProperties.PAGE);
        mainPage = new MainPage();
    }

    @When("^client enters \"(.*)\" in search bar$")
    public void clientEntersInSearchBar(String searchTerm) {
        mainPage.enterSearchTerm(searchTerm);
    }

    @When("^client pushes search button$")
    public void clientPushesSearchButton() {
        resultsPage = mainPage.pressSearchButton();
    }

    @Then("^only items with keyword \"(.*)\" should be filtered out$")
    public void onlyItemsWithKeywordShouldBeFilteredOut(String searchTerm) {
        resultsPage.assertAllResultsContainSearchTerm(searchTerm);
    }

    @Then("^suggestion containing word \"([^\"]*)\" should be displayed$")
    public void suggestionContainingWordShouldBeDisplayed(String searchTerm) {
        mainPage.assertSuggestionsAreDisplayed(searchTerm);
    }

    @When("^client chooses the suggestion$")
    public void clientChoosesTheSuggestion() {
        productPage = mainPage.chooseFirstSuggestion();
    }

    @Then("^product page is opened with word \"(.*)\" in it's name$")
    public void productPageIsOpenedWithWordInItsName(String searchTerm) {
        productPage.assertProductNameContains(searchTerm);
    }

    @Then("^no items are found for search term \"(.*)\"$")
    public void noItemsAreFound(String searchTerm) {
        resultsPage.assertNoResultsReturnedForSearchTerm(searchTerm);
    }

    @Then("^no items are found$")
    public void noItemsAreFound() {
        resultsPage.assertNoResultsReturnedForSearchTerm();
    }

    @When("^client enters (\\d+) characters long string in search bar$")
    public void clientEntersCharactersLongStringInSearchBar(int length) {
        String s = getRandomString(length);
        mainPage.enterSearchTerm(s);
    }

    private String getRandomString(int len) {
        Character[] unicodeChars = IntStream.rangeClosed(33, 126)
                .mapToObj(n -> (char) n).toArray(Character[]::new);

        Random rand = new Random();
        return IntStream.range(0, len)
                .mapToObj(s -> {
                    Character c = unicodeChars[rand.nextInt(unicodeChars.length)];
                    return Character.toString(c);
                })
                .collect(Collectors.joining());
    }

}