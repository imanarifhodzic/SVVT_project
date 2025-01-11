import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class ProductDetailsTests extends SetupAndTearDown {



    @Test
    void productDetailsURLChange(){
        driver.get(baseUrl);
        driver.findElement(By.className("product-image-wrapper")).click();
        assertNotEquals(baseUrl, driver.getCurrentUrl());
    }

    @Test
    void productDetailsTitle(){
        driver.get(baseUrl);
        WebElement product = driver.findElement(By.className("product-image-wrapper"));
        String title =
                driver.findElement(By.xpath(
                        "/html/body/div[1]/section[2]/div/div/div/div[1]/div/div/div/div/div/div[1]/div/div/div/ul[1]/li[1]/div/div[2]/div[1]/span/a"
                )).getText();
        product.click();
        assertEquals(title.substring(0, 25), driver.findElement(By.className("product-title")).getText().substring(0, 25).toUpperCase());

    }

    @Test
    void productDetailsPrice(){
        driver.get(baseUrl);
        String price =
                driver.findElement(By.xpath(
                        "/html/body/div[1]/section[2]/div/div/div/div[1]/div/div/div/div/div/div[1]/div/div/div/ul[1]/li[1]/div/div[2]/div[2]/div/span"
                )).getText();
        driver.findElement(By.className("product-image-wrapper")).click();
        String detailsPrice =
                driver.findElement(By.xpath(
                        "/html/body/div[1]/section[2]/div/div/div/section/div/div[3]/div/div[2]/div[2]/div[1]/div[1]/span[2]"
                )).getText();
        assertEquals(price, detailsPrice);
    }

}
