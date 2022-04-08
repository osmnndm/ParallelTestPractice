package com.parallel.pages;

import com.parallel.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage {

    public HomePage() {
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(id = "search-bar__input")
    public WebElement searchBox;

    @FindBy(css = ".ifm-search-bar__submit.normalize.ifm-button")
    public WebElement searchButton;

//    @FindBy(xpath = "(//*[@class='ng-scope'])[9]/div/span")
    @FindBy(css = ".price-block__listprice.ng-binding.ng-scope span")
    public WebElement price;

    @FindBy(css = ".infobox__headline.ng-binding")
    public WebElement noArtikel;


}
