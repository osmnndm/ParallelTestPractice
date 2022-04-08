@datatable01 @third @second
Feature: scenario outline example
  Scenario: testing the data tables web page
    Given User is on the datatables homepage
    When User clicks on new button
    And User enters first name "Ahmet"
    And User enters last name "Sami"
    And User enters position "SDET"
    And User enters office "New York"
    And User enters extension "222"
    And User enters start date "2018-09-25"
    And User enters salary "100000"
    And User clicks Create button
    And User enters first name "Ahmet" to the searchbox
    Then verify that user see the first name "Ahmet" in the searchbox