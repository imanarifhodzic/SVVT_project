import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EnforcingHTTPSTests {

    private static WebDriver driver;
    private static String baseUrl = "https://books.ba/";


    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Luka/Desktop/WebDriver/chromedriver.exe"); //change pathname
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    void enforceHTTPS() {
        driver.get(baseUrl);
        assertTrue(driver.getCurrentUrl().startsWith("https"));
    }

    @Test
    void mixedContentVerification() { //Insecure resource found: http://www. joomshaper.com/
        driver.get(baseUrl);
        //getting all elements that have src or href attributes
        List<WebElement> elements = driver.findElements(By.xpath("//*[@src or @href]"));

        // Check if all resources use HTTPS by getting the value of the src or href attribute
        // that either starts with "https://" or "http://"
        for (WebElement element : elements) {
            String attributeValue = element.getAttribute("src");
            if (attributeValue == null) {
                attributeValue = element.getAttribute("href");
            }
            //asserting that the attributes value start with https://
            assertTrue(attributeValue.startsWith("https://"),
                    "Insecure resource found: " + attributeValue);


        }
    }

    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }

}
