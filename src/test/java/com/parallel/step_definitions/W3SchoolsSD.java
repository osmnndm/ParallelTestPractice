package com.parallel.step_definitions;

import com.parallel.utilities.Driver;
import com.parallel.utilities.ReusableMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;


import java.util.List;

public class W3SchoolsSD {
    String url = "https://www.w3schools.com/html/html_tables.asp";
    @Given("kullanici w3schools sayfasina gider")
    public void kullanici_w3schools_sayfasina_gider() {
        Driver.get().get(url);

    }

    @Then("kullanici ulke isimlerini alir")
    public void kullanici_ulke_isimlerini_alir() {
        List<WebElement> list = ReusableMethods.getSutundakiHucreler(3);
        for (WebElement s: list) {
            System.out.println(s.getText());
        }

    }


}
