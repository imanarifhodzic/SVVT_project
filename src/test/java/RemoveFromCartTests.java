import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class RemoveFromCartTests extends SetupAndTearDown {

    @Test
    void removeFromCart() throws InterruptedException {
        driver.get(baseUrl);
        driver.findElement(By.className("product-image-wrapper")).click();
        AddToCartTests.addBook();
        driver.findElement(By.className("proopc-clear-cart")).click();
        Thread.sleep(1000);
        String numOfItems = driver.findElement(By.className("total_products")).getText();
        assertEquals("0 proizvoda", numOfItems);
    }

    @Test
    void removeOneBook() throws InterruptedException {
        driver.get(baseUrl);
        driver.findElement(By.className("product-image-wrapper")).click();
        WebElement plus = driver.findElement(By.className("quantity-plus"));
        //adding 2 more books to the cart, total of 3
        plus.click();
        plus.click();
        AddToCartTests.addBook();
        Thread.sleep(1000);
        WebElement numOfItems = driver.findElement(By.name("quantity[0]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', '2')", numOfItems);

        Thread.sleep(2000);
        driver.findElement(By.className("proopc-icon-refresh")).click();
        Thread.sleep(2000);
        assertEquals("2 products", driver.findElement(By.className("total_products")).getText());
    }
}
