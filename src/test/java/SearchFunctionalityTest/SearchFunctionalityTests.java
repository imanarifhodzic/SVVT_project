package SearchFunctionalityTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

//SEARCH FUNCTIONALITY TEST SCENARIO
public class SearchFunctionalityTests {

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
    void searchForBook() throws InterruptedException {
        driver.get("https://books.ba/");
        WebElement searchBar = driver.findElement(By.id("search-area259"));
        searchBar.sendKeys("Six of Crows");
        WebElement searchIcon =
                driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div[2]/div[1]/div/form/div/div[2]/div"));
        searchIcon.click();
        Thread.sleep(1000);
        WebElement book =
                driver.findElement(By.xpath("/html/body/div[1]/section[2]/div/div/div/section/div/div[2]/div/div[3]/div[2]/div/div/div[1]"));
        Thread.sleep(1000);
        book.click();
        WebElement titleOfBook = driver.findElement(By.className("product-title"));
        assertEquals("Six of Crows: Collector's Edition (Book 1)", titleOfBook.getText());

    }

    @Test
    void searchFullTitleOfBook() throws InterruptedException { //FAIL for some reason, if we searched only
        driver.get("https://books.ba/");                       //"Six of Crows", the search would be a success
        WebElement searchBar = driver.findElement(By.id("search-area259"));
        searchBar.sendKeys("Six of Crows: Collector's Edition (Book 1)");
        WebElement searchIcon =
                driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div[2]/div[1]/div/form/div/div[2]/div"));
        searchIcon.click();
        Thread.sleep(1000);
        WebElement book =
                driver.findElement(By.xpath("/html/body/div[1]/section[2]/div/div/div/section/div/div[2]/div/div[3]/div[2]/div/div/div[1]"));
        Thread.sleep(1000);
        book.click();
        WebElement titleOfBook = driver.findElement(By.className("product-title"));
        assertEquals("Six of Crows: Collector's Edition (Book 1)", titleOfBook.getText());

    }


    @Test
    void searchBookThroughDropdown() throws InterruptedException {
        driver.get("https://books.ba/");

        WebElement searchBar = driver.findElement(By.id("search-area259"));
        searchBar.sendKeys("Six of Crows: Collector's Edition");
        Thread.sleep(2500); //because when we search for a book, a dropdown appears, but not right away
        WebElement book = driver.findElement(By.className("result-element")); //dropdown after search
        book.click();
        Thread.sleep(1000);
        WebElement bookTitle = driver.findElement(By.xpath("/html/body/div[1]/section[2]/div/div/div/section/div/div[3]/div/div[2]/h2"));
        assertEquals("Six of Crows: Collector's Edition (Book 1)", bookTitle.getText());
    }

    @Test
    void searchByISBN() throws InterruptedException {
        driver.get("https://books.ba/");
        WebElement searchBar = driver.findElement(By.id("search-area259"));
        searchBar.sendKeys("9781510106284"); // Six Of Crows again
        WebElement searchIcon =
                driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div[2]/div[1]/div/form/div/div[2]/div"));
        searchIcon.click();
        Thread.sleep(1500);
        WebElement book =
                driver.findElement(By.xpath("/html/body/div[1]/section[2]/div/div/div/section/div/div[2]/div/div[3]/div[1]/div/div/div[1]"));
        book.click();
        WebElement titleOfBook = driver.findElement(By.className("product-title"));
        assertEquals("Six of Crows: Collector's Edition (Book 1)", titleOfBook.getText());
    }

    @Test
    void searchByAuthor() throws InterruptedException { // FAIL doesn't display results
        driver.get("https://books.ba/");
        WebElement searchBar = driver.findElement(By.id("search-area259"));
        searchBar.sendKeys("Leigh Bardugo"); // Six Of Crows author
        WebElement searchIcon =
                driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div[2]/div[1]/div/form/div/div[2]/div"));
        searchIcon.click();
        Thread.sleep(2000);
        WebElement book = driver.findElement(By.className("product-details")); // finding first product, throws error
        assertTrue(book.isDisplayed()); // won't even reach this

    }

    @Test
    void searchByPublisher() throws InterruptedException {
        driver.get("https://books.ba/");
        WebElement searchBar = driver.findElement(By.id("search-area259"));
        searchBar.sendKeys("Orion Books"); // Six Of Crows author
        WebElement searchIcon =
                driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div[2]/div[1]/div/form/div/div[2]/div"));
        searchIcon.click();
        Thread.sleep(1000);
        WebElement book =
                driver.findElement(By.xpath("/html/body/div[1]/section[2]/div/div/div/section/div/div[2]/div/div[3]/div[1]/div/div/div[1]")); // finding first product
        Thread.sleep(1000);
        book.click();
        Thread.sleep(1000);
        WebElement publisher =
                driver.findElement(By.xpath("/html/body/div[1]/section[2]/div/div/div/section/div/div[3]/div/div[2]/div[2]/div[5]/a[2]"));
        assertEquals("Orion Books", publisher.getText());

    }

    @Test
    void noResultSearch(){
        driver.get("https://books.ba/");
        WebElement searchBar = driver.findElement(By.id("search-area259"));
        searchBar.sendKeys("Bible"); // Six Of Crows author
        WebElement searchIcon =
                driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div[2]/div[1]/div/form/div/div[2]/div"));
        searchIcon.click();
        assertTrue(driver.getPageSource().contains("Nema rezultata : (Bible)"));
    }



    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
