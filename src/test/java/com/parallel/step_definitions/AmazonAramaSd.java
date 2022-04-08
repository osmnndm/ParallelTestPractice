package com.parallel.step_definitions;

import com.parallel.pages.AmazonArama;
import com.parallel.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.Keys;


public class AmazonAramaSd {

    AmazonArama amazonArama = new AmazonArama();


    @Given("kullanıcı amazon sayfasına gider")
    public void kullanici_amazon_sayfasina_gider() {

        Driver.get().get("https:/amazon.com");
    }

    @Given("kullanıcı arama kutusuna iphone yazar")
    public void kullanici_arama_kutusuna_iphone_yazar() {

        amazonArama.amazonArama.sendKeys("iphone" + Keys.ENTER);
    }

    @Then("kullanıcı sonuc sayisini ekrana yazar")
    public void kullanici_sonuc_sayisini_ekrana_yazar() {
       String sonucSayisi = amazonArama.sonuc.getText();
        System.out.println(sonucSayisi);
    }

}
