import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddToCartTests extends SetupAndTearDown {

    static void addBook() throws InterruptedException {
        driver.findElement(By.name("addtocart")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("showcart")).click();
        Thread.sleep(1000);
    }

    @Test
    void addToCart() throws InterruptedException {
        driver.get(baseUrl);
        driver.findElement(By.className("product-image-wrapper")).click();
        String title = driver.findElement(By.className("product-title")).getText();
        addBook();
        String cartTitle =
                driver.findElement(By.xpath(
                        "/html/body/div[1]/section[2]/div/div/div/section/div/div[3]/form/div/table/tbody/tr[1]/td[1]/div/a"
                )).getText();
        assertEquals(title.toUpperCase(), cartTitle);
    }

    @Test
    void addToCartMultipleOfSameBook() throws InterruptedException {
        driver.get(baseUrl);
        driver.findElement(By.className("product-image-wrapper")).click();
        String title = driver.findElement(By.className("product-title")).getText();
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[2]/div/div/div/section/div/div[3]/div/div[2]/div[2]/div[2]/form/div[2]/span[2]/input[1]"
        )).click();
        Thread.sleep(1000);
        addBook();
        Thread.sleep(1000);
        String totalProducts = driver.findElement(By.className("total_products")).getText();
        assertEquals("2 products", totalProducts);

    }
}
