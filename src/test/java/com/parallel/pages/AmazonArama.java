package com.parallel.pages;

import com.parallel.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AmazonArama {

    public AmazonArama(){

        PageFactory.initElements(Driver.get(),this);
    }
    @FindBy( id = "twotabsearchtextbox")
    public WebElement amazonArama;

    @FindBy( xpath = "//h1/div/div/div/div/span[1]")
    public WebElement sonuc;
}
