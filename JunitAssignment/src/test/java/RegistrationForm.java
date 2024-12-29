import org.junit.jupiter.api.*;
import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegistrationForm {
    WebDriver driver;
    Faker faker;

    @BeforeAll
    public void setup() {
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--headed");
        driver = new ChromeDriver(ops);
        driver.manage().window().maximize();
        faker = new Faker();
    }

    @Test
    public void registrationWebFrom() throws InterruptedException {


        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form");

        List<WebElement> registerFields = driver.findElements(By.className("ur-frontend-field"));

        WebElement name1Field = driver.findElement(By.name("first_name"));
        name1Field.sendKeys("Alisha");

        WebElement name2Field = driver.findElement(By.name("last_name"));
        name2Field.sendKeys("Ibnat");

        String Name = faker.name().firstName();
        String emailAddress = Name.toLowerCase()+"70@gmail.com";
        registerFields.get(1).sendKeys(emailAddress);

        WebElement passwordField = driver.findElement(By.name("user_pass"));
        passwordField.sendKeys("Ai@#12fg.3");

        WebElement phoneField = driver.findElement(By.name("phone_1665627880"));
        phoneField.sendKeys("01625489715");
        WebElement nationalityField = driver.findElement(By.name("input_box_1665629217"));
        nationalityField.sendKeys("Bangladeshi");


        List<WebElement> radio = driver.findElements(By.className("radio"));
        Actions actions = new Actions(driver);
        actions.click(radio.get(1)).perform();


        Select options = new Select(driver.findElement(By.id("country_1665629257")));
        options.selectByVisibleText("Bangladesh");

        List<WebElement> BirthDayElement = driver.findElements(By.cssSelector("[type=text]"));
        WebElement BirthDay = BirthDayElement.get(2);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', '2000-08-27')", BirthDay);

        driver.findElement(By.id("privacy_policy_1665633140")).click();

        Utils.scroll(driver, 1000);

        WebElement submitButtonElement = driver.findElement(By.className("ur-submit-button"));
        submitButtonElement.click();

    }

    @AfterAll
    public void closeDriver() throws InterruptedException {
        //driver.quit();
    }
}


