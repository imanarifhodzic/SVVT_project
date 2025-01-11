
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EnforcingHTTPSTests extends SetupAndTearDown {


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


}
