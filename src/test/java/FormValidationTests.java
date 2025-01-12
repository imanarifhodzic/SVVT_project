import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;

public class FormValidationTests extends SetupAndTearDown {

    @Test
    void invalidInput() {

        driver.get(baseUrl + "/knjizara/books-on-demand");

        driver.findElement(By.id("field45")).sendKeys("Ronaldo");
        driver.findElement(By.id("field46")).sendKeys("not an email");
        driver.findElement(By.id("field47")).click();
        boolean error = driver.findElement(By.id("field46-error")).isDisplayed();
        assertTrue(error); // error message is displayed
    }

    @Test
    void validInput() throws InterruptedException {

        driver.get(baseUrl + "/knjizara/books-on-demand");

        driver.findElement(By.id("field45")).sendKeys("Ime i Prezime");
        driver.findElement(By.id("field46")).sendKeys("ime@prezime.com");
        driver.findElement(By.id("field47")).sendKeys("123456789");
        driver.findElement(By.id("field53")).sendKeys("Grad");

        driver.findElement(By.id("field48")).sendKeys("Knjiga");
        driver.findElement(By.id("field49")).sendKeys("Autor");
        driver.findElement(By.id("field85_1")).click();
        driver.findElement(By.id("field61")).sendKeys("1");
        driver.findElement(By.id("field52")).sendKeys("Zamolio bih da zanemarite ovaj zahtjev.");
        Thread.sleep(17000);
        driver.findElement(By.id("field57")).click();
        boolean success = driver.getPageSource().contains("Hvala Vam na upitu za ponudu!");
        assertTrue(success); // success message is displayed

    }
}
