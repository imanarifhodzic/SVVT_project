package testing;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleTest {

    private WebDriver driver;

    @BeforeEach
    void setup() {
        System.setProperty("webdriver.chrome.driver",
                "/Users/iman/Downloads/WebDriver/chromedriver");


        driver = new ChromeDriver();
    }

    @Test
    void openBooksHomepage() {
        driver.get("https://www.google.com");
        Assertions.assertEquals("Google", driver.getTitle(), "Title does not match!");
        System.out.println(driver.getTitle() + " is the title of the page.");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
