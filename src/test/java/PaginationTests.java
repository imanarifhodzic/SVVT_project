import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.*;

public class PaginationTests extends SetupAndTearDown {

    @Test
    void numberOfItemsOnPage(){
        driver.get(baseUrl + "knjizara/poezija-drama-i-eseji");
        String numberOfItems =
                driver.findElement(By.xpath(
                        "/html/body/div[1]/section[2]/div/div/div/section/div/div[2]/div/div[1]/div[2]/select/option[1]"
                        )).getText();
        int items = driver.findElements(By.className("product-image-wrapper")).size();
        assertEquals(numberOfItems, String.valueOf(items));
    }

    @Test
    void nextPage(){
        driver.get(baseUrl + "knjizara/poezija-drama-i-eseji");
        String currentUrl = driver.getCurrentUrl();
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[2]/div/div/div/section/div/div[2]/div/div[11]/ul/li[5]/a"
        )).click();
        assertNotEquals(currentUrl, driver.getCurrentUrl());
    }

    @Test
    void changingPaginationLimit(){
        driver.get(baseUrl + "knjizara/poezija-drama-i-eseji");
        Select paginationLimit = new Select(driver.findElement(By.id("limit")));
        paginationLimit.selectByValue("/knjizara/poezija-drama-i-eseji?limit=40");
        //for some reason, value isn't 40, but part of the URL /knjizara/poezija-drama-i-eseji?limit=40
        int items = driver.findElements(By.className("product-image-wrapper")).size();
        assertEquals(40, items);

    }
}
