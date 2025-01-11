import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class RegistrationAndLoginTests {

    private static WebDriver driver;



    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Luka/Desktop/WebDriver/chromedriver.exe"); //change pathname
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @Test
    void userRegistrationValidInputs(){ //TODO skontaj ovo zivota ti i razdvoji login i register kao dva test scenarija
        driver.get("https://books.ba/knjizara/korisnik");

        WebElement email = driver.findElement(By.id("email_field"));

    }


    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
