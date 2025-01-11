import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.*;

public class FilteringAndSortingTests extends SetupAndTearDown {

    @Test
    void sortByDate() throws InterruptedException {
        driver.get(baseUrl + "knjizara/beletristika");


        driver.findElement(By.xpath(
                "/html/body/div[1]/section[2]/div/div/div/section/div/div[2]/div/div[1]/div[1]/div[1]/div[2]/a"
        )).click();
        Thread.sleep(1000);
        assertEquals("https://books.ba/knjizara/beletristika/dirAsc", driver.getCurrentUrl());

    }

    @Test
    void filterByCategory() throws InterruptedException {
        driver.get(baseUrl);
        String homeUrl = driver.getCurrentUrl();

        WebElement categories =
                driver.findElement(By.xpath(
                        "/html/body/div[1]/section[1]/div/div/div[2]/div/ul/li[2]/a"
                ));
        Actions actions = new Actions(driver);
        actions.moveToElement(categories).perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[1]/div/div/div[2]/div/ul/li[2]/div/div/div/div[2]/ul/li[3]/a/span"
        )).click();
        String romantikaUrl = driver.getCurrentUrl();
        assertNotEquals(homeUrl, romantikaUrl);
        assertEquals("https://books.ba/knjizara/romantika", driver.getCurrentUrl());

    }
}
