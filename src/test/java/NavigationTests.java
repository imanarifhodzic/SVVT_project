
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


import static org.junit.jupiter.api.Assertions.*;



public class NavigationTests extends SetupAndTearDown {


    @Test
    void navigationCorrectURLsMenuItems() throws InterruptedException {
        driver.get(baseUrl);
        //categories
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[1]/div/div/div[2]/div/ul/li[2]/a/span/span"
        )).click();
        assertEquals("https://books.ba/knjizara", driver.getCurrentUrl());
        //home
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[1]/div/div/div[2]/div/ul/li[1]/a/span/span"
        )).click();
        assertEquals("https://books.ba/", driver.getCurrentUrl());
        //cesta pitanja
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[1]/div/div/div[2]/div/ul/li[3]/a/span/span"
        )).click();
        assertEquals("https://books.ba/cesta-pitanja", driver.getCurrentUrl());
        //lokacija
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[1]/div/div/div[2]/div/ul/li[5]/a/span/span"
        )).click();
        assertEquals("https://books.ba/gdje-se-nalazimo", driver.getCurrentUrl());
        //kontakt
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[1]/div/div/div[2]/div/ul/li[6]/a/span/span"
        )).click();
        assertEquals("https://books.ba/kontakt", driver.getCurrentUrl());
        //registracija
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[1]/div/div/div[1]/div[3]/span[2]/a"
        )).click();
        assertEquals("https://books.ba/knjizara/korisnik", driver.getCurrentUrl());
        //cart
        driver.findElement(By.xpath(
                "/html/body/div[1]/header/div/div/div[3]/div/div[2]/i"
        )).click();
        driver.findElement(By.xpath(
                "/html/body/div[1]/header/div/div/div[3]/div/div[2]/ul/li[4]/a"
        )).click();
        assertEquals("https://books.ba/korpa", driver.getCurrentUrl());

    }

    @Test
    void navigationCorrectURLsFooter() throws InterruptedException {
        driver.get(baseUrl);
        //o nama
        Thread.sleep(1000);
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[4]/div/div/div[1]/div[1]/div/div/div/p[1]/a[1]"
        )).click();
        assertEquals("https://books.ba/o-nama", driver.getCurrentUrl());
        //prednosti
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[3]/div/div/div[1]/div[1]/div/div/div/p[1]/a[2]"
        )).click();
        assertEquals("https://books.ba/prednosti", driver.getCurrentUrl());
        //radno vrijeme
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[3]/div/div/div[1]/div[1]/div/div/div/p[1]/a[4]"
        )).click();
        assertEquals("https://books.ba/radno-vrijeme", driver.getCurrentUrl());
        //gift
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[3]/div/div/div[1]/div[1]/div/div/div/p[1]/a[7]"
        )).click();
        assertEquals("https://books.ba/gift", driver.getCurrentUrl());
    }

    @Test
    void correctYearInFooter(){ //but the page doesn't get the year with js, it is hardcoded
        driver.get(baseUrl);
        assertTrue(driver.getPageSource().contains("Autorska prava © 2025 books.ba - Sva prava zadržana."));
    }

}
