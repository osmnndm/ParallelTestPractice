package com.parallel.pages;

import com.parallel.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

public class IfmPage {

    public IfmPage() {
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(css = ".countries li a")
    public List<WebElement> countries;

    @FindBy(xpath = "//*[.=\"Trade Partners and others\"]")
    public WebElement others;

    @FindBy(xpath = " //*[.=\" ifm Global Website: English\"]")
    public WebElement globalWebsite;

    @FindBy(css = ".uc-btn-accept-wrapper #uc-btn-accept-banner")
    public WebElement agree;


}
