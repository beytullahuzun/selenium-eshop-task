@shoppingTest
Feature: Search functionality on http://automationpractice.com

  Scenario: Client can search for desired clothing using search bar
    Given client is at main page
    When client enters "shirt" in search bar
    And client pushes search button
    Then only items with keyword "shirt" should be filtered out

  Scenario: Client can search for desired clothing using search bar's suggestions
    Given client is at main page
    When client enters "shir" in search bar
    Then suggestion containing word "shirt" should be displayed
    When client chooses the suggestion
    Then product page is opened with word "shir" in it's name

  Scenario: No items are returned if client enters search term with no matches
    Given client is at main page
    When client enters "trousers" in search bar
    And client pushes search button
    Then no items are found for search term "trousers"

  Scenario: Invalid characters in search term are ignored 
    Given client is at main page
    When client enters "!@#$%^&*()_  +shirt{}  [];:'",.<>/?" in search bar
    And client pushes search button
    Then only items with keyword "shirt" should be filtered out

  Scenario: No items are returned if client enters too long string
    Given client is at main page
    When client enters 300 characters long string in search bar
    And client pushes search button
    Then no items are found