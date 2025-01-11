import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTests extends SetupAndTearDown {

    @Test
    void loginWithValidCredentials() {
        driver.get(baseUrl + "/prijava");
        driver.findElement(By.id("username")).sendKeys("example@gmail.com"); //email used in registration test
        driver.findElement(By.id("password")).sendKeys("password123"); //password used in registration test
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[2]/div/div/div/section/div/div[2]/form/fieldset/div[3]/div/button"
        )).click();

        assertEquals("Prijava uspješna.", driver.findElement(By.className("alert-message")).getText());

    }

    @Test
    void loginWithInvalidCredentials() {
        driver.get(baseUrl + "/prijava");
        driver.findElement(By.id("username")).sendKeys("caocaozdravo123"); //email used in registration test
        driver.findElement(By.id("password")).sendKeys("f"); //password used in registration test
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[2]/div/div/div/section/div/div[2]/form/fieldset/div[3]/div/button"
        )).click();
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            String errorMessage =
                    driver.findElement(By.xpath(
                            "/html/body/div[1]/section[2]/div/div/div/section/div/div/div/div"
                    )).getText();
            assertEquals("E-mail i šifra se ne slažu ili još nemate pristup", errorMessage);
        } catch (NoAlertPresentException e) {
            // No alert present, continue with the test
        }
    }

}
