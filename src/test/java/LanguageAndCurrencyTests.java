
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class LanguageAndCurrencyTests extends SetupAndTearDown {



    void BAMtoEuro() throws InterruptedException {
        //changing currency to EUR
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[1]/div/div/div[1]/div[1]/form/div[1]/div/div/span"
        )).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[1]/div/div/div[1]/div[1]/form/div[1]/div/ul/li[2]/a"
        )).click();
    }

    void EuroToBAM() throws InterruptedException {
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[1]/div/div/div[1]/div[1]/form/div[1]/div/div/span"
        )).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[1]/div/div/div[1]/div[1]/form/div[1]/div/ul/li[1]/a"
        )).click();
    }


    @Test
    void changeLanguage() throws InterruptedException {

        driver.get(baseUrl);

        //changing language to English
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[1]/div/div/div[1]/div[2]/ul/li[2]/a"
        )).click();
        assertEquals("https://books.ba/en/", driver.getCurrentUrl());
        Thread.sleep(1000);
        //changing language to Bosnian
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[1]/div/div/div[1]/div[2]/ul/li[1]/a/img"
        )).click();
        assertEquals("https://books.ba/", driver.getCurrentUrl());
    }

    @Test
    void changingCurrencyToEuro() throws InterruptedException { //FAIL for the home page, works for the cart and product page

        driver.get(baseUrl);
        //changing currency to EUR
        BAMtoEuro();

        WebElement price = driver.findElement(By.className("product-price"));
        assertEquals("€", price.getText().substring(price.getText().length() - 1));

    }

    @Test
    void changingCurrencyToBAM() throws InterruptedException {

        driver.get(baseUrl);
        //changing currency to BAM
        EuroToBAM();

        WebElement price = driver.findElement(By.className("product-price"));
        assertEquals("KM", price.getText().substring(price.getText().length() - 2));

    }

    @Test
    void changingCurrencyInProductPage() throws InterruptedException {
        driver.get(baseUrl);
        WebElement product = driver.findElement(By.className("product-image-wrapper"));
        product.click();
        Thread.sleep(1000);
        //changing currency to EUR
        BAMtoEuro();

        WebElement price =
                driver.findElement(By.xpath(
                        "/html/body/div[1]/section[2]/div/div/div/section/div/div[3]/div/div[2]/div[2]/div[1]/div[1]/span[2]"
                ));
        assertEquals("€", price.getText().substring(price.getText().length() - 1));

        //changing currency to BAM
        EuroToBAM();
        Thread.sleep(1000);
        //threw StaleElementReferenceException, so i just found the element again, sloppy i know
        WebElement priceBAM = driver.findElement(By.xpath(
                "/html/body/div[1]/section[2]/div/div/div/section/div/div[3]/div/div[2]/div[2]/div[1]/div[1]/span[2]"
        ));
        assertEquals("KM", priceBAM.getText().substring(priceBAM.getText().length() - 2));

    }

    @Test
    void changingCurrencyInCart() throws InterruptedException {
        driver.get(baseUrl);
        //adding a product to the cart
        WebElement product = driver.findElement(By.className("product-image-wrapper"));
        product.click();
        Thread.sleep(1000);
        driver.findElement(By.name("addtocart")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("showcart")).click();
        Thread.sleep(1000);
        //changing currency to EUR
        BAMtoEuro();

        WebElement price =
                driver.findElement(By.xpath(
                "/html/body/div[1]/section[2]/div/div/div/section/div/div[3]/form/div/table/tbody/tr[1]/td[6]/div/span[2]"
        ));
        assertEquals("€", price.getText().substring(price.getText().length() - 1));
        Thread.sleep(1000);
        //changing currency to BAM
        EuroToBAM();
        //redefining it again
        WebElement price2 =
                driver.findElement(By.xpath(
                        "/html/body/div[1]/section[2]/div/div/div/section/div/div[3]/form/div/table/tbody/tr[1]/td[6]/div/span[2]"
                ));
        assertEquals("KM", price2.getText().substring(price2.getText().length() - 2));


    }
}
