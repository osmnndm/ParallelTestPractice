package com.parallel.utilities;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class ReusableMethods {


    public static void getDefaultMessage(String message) {
        WebElement element = Driver.get().findElement(By.xpath("//span[contains(text(),'" + message + "')]"));

        String actual = element.getText();
        System.out.println(actual);

        Assert.assertTrue("message is not displayed ", actual.contains(message));

    }


    public static void selectDropDown(String dropdown) {
        WebElement element = Driver.get().findElement(By.xpath("//select[@id='" + dropdown + "']"));
        Select select = new Select(element);
        List<WebElement> options = select.getOptions();
        for (WebElement each : options) {
            System.out.println(each.getText());
        }

    }

    public static String getScreenshot(String name) throws IOException {
        // naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        // TakesScreenshot is an interface of selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot) Driver.get();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";

        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);


        return target;

    }

    //========Switching Window=====//
    public static void switchToWindow(String targetTitle) {
        String origin = Driver.get().getWindowHandle();
        for (String handle : Driver.get().getWindowHandles()) {
            Driver.get().switchTo().window(handle);
            if (Driver.get().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.get().switchTo().window(origin);
    }

    //========Hover Over=====//
    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.get());
        actions.moveToElement(element).click().perform();

    }

    //==========Return a list of string given a list of Web Element====////
    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }

    //========Returns the Text of the element given an element locator==//
    public static List<String> getElementsText(By locator) {
        List<WebElement> elems = Driver.get().findElements(locator);
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : elems) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }

    //===============Implicity Wait==============//
    public static void impWait(int TimeOut){
        Driver.get().manage().timeouts().implicitlyWait(TimeOut, TimeUnit.SECONDS);
    }

    //===============Explicit Wait==============//

    public static void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickablility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            WebDriverWait wait = new WebDriverWait(Driver.get(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(
                    "Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
        }
    }

    //======Fluent Wait====//
//    public static WebElement fluentWait(final WebElement webElement, int timeinsec) {
//        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver())
//                .withTimeout(timeinsec, TimeUnit.SECONDS).pollingEvery(timeinsec, TimeUnit.SECONDS)
//                .ignoring(NoSuchElementException.class);
//        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
//            public WebElement apply(WebDriver driver) {
//                return webElement;
//            }
//        });
//        return element;
//    }

    //======Fluent Wait====//
    public static WebElement fluentWait(final WebElement webElement, int timeinsec) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.get())
                .withTimeout(Duration.ofSeconds(timeinsec))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        return wait.until(new Function<WebDriver, WebElement>()
        {
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });
    }

    //TakeScreenshot
    public static void TakeScreenshot(String name) {
        TakesScreenshot takeScreenshot = (TakesScreenshot) Driver.get();
        File kaynak = takeScreenshot.getScreenshotAs(OutputType.FILE);

        File goruntu = new File(System.getProperty("user.dir") + name + ".png");

        try {
            FileUtils.copyFile(kaynak, goruntu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Bir WebElementin yazısını degistirme
    public static void WebElementYazisiDegistirme(String url, WebElement locate, String newText) {
        Driver.get().get(url);
        JavascriptExecutor js = (JavascriptExecutor) Driver.get();
        String newText1 = "arguments[0].innerText = '" + newText + "'";
        js.executeScript(newText1, locate);
    }


    // Dropdown seçeneklerini yazdırma
    public static void dropDownSecenekler(String url, WebElement dropDown) {
        Driver.get().get(url);
        Select select = new Select(dropDown);
        List<WebElement> secenekler = select.getOptions(); // tüm seçenekleri almak için kullanılan method

        for (WebElement w : secenekler) {
            System.out.println(w.getText());
        }
    }

    // switch to window
    public static void switchToChildWindow()  {
        String origin="";
        for (String handle : Driver.get().getWindowHandles()) {
            origin = handle;
        }
        Driver.get().switchTo().window(origin);
    }

    // arka plan rengini alma
    public static void cssRenkKodu(String url, WebElement locate, String cssValue, String colorConvert) {
        Driver.get().get(url);
        String arkaplanRengi = locate.getCssValue(cssValue);
        boolean gleich = arkaplanRengi.equals(colorConvert);
        System.out.println(arkaplanRengi);
        // stil kısmında renk kodu : #04AA6D yazıyor buna Hex kodu deniyor.
        // programı çalıştırınca bize rgba(4, 170, 109, 1) veriyor. bu değeri internetten color converter araması yapıp
        // çıkan sitede çevirebiliyoruz.
    }

    //reCAPTCHA doğrulaması, doğrulamaları manuel yapıp bekliyoruz.
    public static void reCaptcha(String url, WebElement box, WebElement send) throws InterruptedException {
        Driver.get().get(url);
        Thread.sleep(2000);
        box.click();
        Thread.sleep(15000);
        send.click();
    }

    //Dosya indirme işleminin doğrulanması
    public static void download(String url, WebElement downloadButton, String fileName) throws InterruptedException {
        Driver.get().get(url);
        downloadButton.click();

        Thread.sleep(5000);

        File file = new File(fileName);
        boolean isDownload = file.exists(); // exist() methodu dosyanın mevcut olup olmaması ile
        // ilgili boolean dönüş veriyor
        System.out.println(isDownload);
        Assert.assertTrue(isDownload);
    }

    //Dosya yükleme
    public static void upload(String url, WebElement uploadButton, WebElement fileUploadInput, String fileAdress) throws InterruptedException {
        Driver.get().get(url);
        uploadButton.click();
        fileUploadInput.sendKeys(fileAdress);

    }

    //Web tablosundaki verilerin sıralı olup olmadğının kontrolü
    public static void tabloSirali(String url, WebElement firstName, List<WebElement> veriler) {
        Driver.get().get(url);
        firstName.click();

        // List<WebElement> veriler = Driver.getDriver().findElements(By.xpath("//div[@role='row'[1]/div[1]"));
        System.out.println(siraliMi(veriler));
    }

    public static boolean siraliMi(List<WebElement> veriler) {
        boolean sirali = false;
        for (int i = 1; i < veriler.size(); i++) {
            WebElement suan = veriler.get(i);
            WebElement birSonraki = veriler.get(i + 1);

            if (birSonraki.getText().equals(" ")) {
                break;
            }

            if (suan.getText().compareTo(birSonraki.getText()) <= 0) {
                sirali = true;
            } else {
                sirali = false;
                break;
            }
        }
        return sirali;
    }

    // iframe'e geçiş yapma
    public static void iFrame(WebElement iframeElement) {
//        Driver.getDriver().get(url);
        Driver.get().switchTo().frame(iframeElement); // iframe'e geçiş yap.
//        Driver.getDriver().switchTo().defaultContent(); // işlem bitince ana sayfaya geri dön.
    }

    // tarih seçimi(date picker)
    public static void tarihSecimi(String url, WebElement tarihInputu, WebElement monthDropdown, String month, WebElement tarih) throws InterruptedException {
        Driver.get().get(url);
        tarihInputu.click();

        Thread.sleep(3000);
        Select select = new Select(monthDropdown); // ay dropdownu seç
        select.selectByVisibleText(month);

        Thread.sleep(1000);

        tarih.click();
    }

    // javaScript kullanarak sendKeys() yapma
    public static void sendKeysJS(String url, WebElement userName) {
        Driver.get().get(url);
        JavascriptExecutor executer = (JavascriptExecutor) Driver.get();
        executer.executeScript("document.getElementById('userName').value = 'Hamza Yılmaz'");
        // executeScript ile JS kodları ile yaptırmak istediğimiz işlemi parantez içerisine yazıyoruz.
    }

    // Thread.sleep methodunu kullanarak bekletme
    public static void sleep(int miliseconds)  {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Tablo başlığı, tablo satırları, tablo sütunlarını yazdırma
    public static void tabloVerileri(List<WebElement> findElem){
        List<WebElement> elements = new ArrayList<>(findElem);
        for (WebElement elm: elements){
            System.out.println(elm.getText());
        }
    }

    // Tablodaki Baslikları alma
    public static List<WebElement> getBasliklar(){
        return Driver.get().findElements(By.xpath("(//table)[1]//th"));
    }

    //Tablodaki Satırları alma
    public static List<WebElement> getSatirlar(){
        return Driver.get().findElements(By.xpath("(//table)[1]/tbody//td"));
    }

    //Tablodaki Tüm hücreleri alma
    public static List<WebElement> getTumHucreler(){
        return Driver.get().findElements(By.xpath("(//table)[1]/tbody//td"));
    }

    //Tablodaki Satırdaki hücreleri alma(){
    public static List<WebElement> getSatirdakiHucreler(int satir) {
        return Driver.get().findElements(By.xpath("((//table)[1]/tbody/tr)["+(satir+1)+"]"));
    }

    //Tablodaki Sutündaki hücreleri alma(){
    public static List<WebElement> getSutundakiHucreler(int sutun) {
        return Driver.get().findElements(By.xpath("(//table)[1]//tr/td["+sutun+"]"));
    }

    //Tablodaki bir satır ve sütundaki hücreyi alma
    public static WebElement getSatirSutun(int satir,int sutun) {
        return Driver.get().findElement(By.xpath("(//table)[1]//tr["+(satir+1)+"]/td["+sutun+"]"));
    }



}