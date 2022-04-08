@SearchResult   @smokeTest

Feature: search and return the list price for entered items.

  Scenario Outline: User goes to the website, performs a search and learns the price from the item.

    Given user goes to the address "https://www.ifm.com/"
    When select a country
    And enter an "<articleName>" as a search term
    And click on the search button
    Then takes the price of the "<articleName>" on the page and prints it on the console

 # Geben Sie die Artikelbezeichnung ein:
    Examples:
    |articleName|
    |VVB001     |
    #|Apple      |

