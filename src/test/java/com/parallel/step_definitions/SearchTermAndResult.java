package com.parallel.step_definitions;

import com.github.javafaker.Faker;
import com.parallel.pages.HomePage;
import com.parallel.pages.IfmPage;
import com.parallel.utilities.Driver;
import com.parallel.utilities.ReusableMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchTermAndResult extends ReusableMethods {

    IfmPage ifmPage = new IfmPage();
    HomePage homePage = new HomePage();
    String price;


    @Given("user goes to the address {string}")
    public void userGoesToTheAddress(String url) {
        Driver.get().get(url);
    }


    @When("select a country")
    public void selectACountry() {
        List<WebElement> countrieList = new ArrayList<>(ifmPage.countries);

        Faker faker = new Faker();
        int countrieIndex = countrieList.size()-1;
        int randomCountrie = faker.number().numberBetween(0,countrieIndex);

        if (randomCountrie<55){
            countrieList.get(randomCountrie).click();
            Driver.get().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//            waitFor(2);
            try {
                ifmPage.agree.click();
            }
            catch (Exception ignored){
            }
        }else {
            ifmPage.others.click();
//          waitForVisibility(countrieList.get(randomCountrie),2);
            hover(countrieList.get(randomCountrie));
            Driver.get().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//            waitFor(1);
            ifmPage.globalWebsite.click();
            Driver.get().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//            waitFor(1);
            switchToChildWindow();

            try {
                ifmPage.agree.click();
            }
            catch (Exception ignored){
            }
        }
    }


    @And("enter an {string} as a search term")
    public void enterAnAsASearchTerm(String articleName) {
        homePage.searchBox.sendKeys(articleName);
        System.out.println();
        System.out.println("Geben Sie die Artikelbezeichnung ein:");
        System.out.println(articleName);
    }


    @And("click on the search button")
    public void clickOnTheSearchButton() {
        Driver.get().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//        waitFor(2);
        homePage.searchButton.click();
    }


    @Then("takes the price of the {string} on the page and prints it on the console")
    public void takesThePriceOfTheOnThePageAndPrintsItOnTheConsole(String articleName) {
        try {
            price = homePage.price.getText();
            System.out.println("Der " + articleName + " kostet " + price);
        } catch (Exception NoSuchElementException) {
            System.out.println("Der Preis ist nicht vorhanden.");
        }

        try {
                String noResult = homePage.noArtikel.getText();
                System.out.println(noResult);
            } catch (Exception ignored){
            }
        }


}

