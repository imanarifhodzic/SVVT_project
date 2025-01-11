import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationTests extends SetupAndTearDown{


    //FAIL does not want to register in any way, nothing even saved to local storage
    @Test
    void validCredentials() throws InterruptedException {
        driver.get(baseUrl + "/knjizara/korisnik");
        driver.findElement(By.id("email_field")).sendKeys("example@gmail.com");
        driver.findElement(By.id("password_field")).sendKeys("password123");
        driver.findElement(By.id("password2_field")).sendKeys("password123");
        driver.findElement(By.id("first_name_field")).sendKeys("Iman");
        driver.findElement(By.id("last_name_field")).sendKeys("Arifhodzic");
        driver.findElement(By.id("address_1_field")).sendKeys("Titova 1");
        driver.findElement(By.id("last_name_field")).sendKeys("Arifhodzic");
        driver.findElement(By.id("zip_field")).sendKeys("71000");
        driver.findElement(By.id("city_field")).sendKeys("Sarajevo");
        Select country = new Select(driver.findElement(By.id("virtuemart_country_id_field")));
        country.selectByValue("27");
        driver.findElement(By.id("phone_1_field")).sendKeys("123456789");
        //time to solve reCAPTCHA
        Thread.sleep(15000);
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[2]/div/div/div/section/div/form/div[2]/button[1]"
        )).click();
        Thread.sleep(4000);
        boolean successMessage = driver.getPageSource().contains("You have successfully registered!");
        assertTrue(successMessage);

    }

    @Test
    void invalidCredentials() throws InterruptedException {
        driver.get(baseUrl + "/knjizara/korisnik");
        driver.findElement(By.id("email_field")).sendKeys("example@");
        driver.findElement(By.id("password_field")).sendKeys("password123");
        driver.findElement(By.id("password2_field")).sendKeys("password123");
        driver.findElement(By.id("first_name_field")).sendKeys("Iman");
        driver.findElement(By.id("last_name_field")).sendKeys("Arifhodzic");
        driver.findElement(By.id("address_1_field")).sendKeys("Titova 1");
        driver.findElement(By.id("last_name_field")).sendKeys("Arifhodzic");
        driver.findElement(By.id("zip_field")).sendKeys("71000");
        driver.findElement(By.id("city_field")).sendKeys("Sarajevo");
        Select country = new Select(driver.findElement(By.id("virtuemart_country_id_field")));
        country.selectByValue("27");
        driver.findElement(By.id("phone_1_field")).sendKeys("123456789");
        //time to solve reCAPTCHA
        Thread.sleep(15000);
        driver.findElement(By.xpath(
                "/html/body/div[1]/section[2]/div/div/div/section/div/form/div[2]/button[1]"
        )).click();
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            String errorMessage =
                    driver.findElement(By.xpath(
                            "/html/body/div[1]/section[2]/div/div/div/section/div/div/div/div"
                    )).getText();
            assertEquals("Neispravno polje:  E-Mail", errorMessage);
        } catch (NoAlertPresentException e) {
            // No alert present, continue with the test
        }

    }
}
