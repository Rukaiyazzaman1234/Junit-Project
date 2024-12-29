import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;




import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)


public class WebForm {
    WebDriver driver;
    private Assertions Assert;


    @BeforeAll


    public void setup() {
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--headed");
        driver = new ChromeDriver(ops);
    }


    @Test
    public void submitWebForm() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");

        driver.findElement(By.id("onetrust-accept-btn-handler")).click();

        List<WebElement> webformFields = driver.findElements(By.className("form-control"));

        webformFields.get(0).sendKeys("Alisha Ibnat");
        webformFields.get(1).sendKeys(Utils.generatePhoneNumber());
        webformFields.get(2).sendKeys(new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
        webformFields.get(3).sendKeys("ai@test.com");
        webformFields.get(4).sendKeys("I am a student");

        System.out.println(System.getProperty("user.dir") + "/src/test/resources/austria.jpg");
        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys(System.clearProperty("user.dir")+ "/src/test/resources/austria.jpg");
        sleep(3000);

        Utils.scroll(driver,500);

        List<WebElement> Elements = driver.findElements(By.id("edit-age"));
        Actions action = new Actions(driver);
        action.click(Elements.get(0)).perform();

        List<WebElement> btnElements = driver.findElements(By.id("edit-submit"));
        Actions actions = new Actions(driver);
        actions.click(btnElements.get(0)).perform();

        driver.get("https://www.digitalunite.com/node/5932/webform/confirmation?token=xEuWxdIX2OG9utfg8vTrh4-51uURDf0jkcfcEmCThMs");
        String text= driver.findElement(By.id("block-pagetitle-2")).getText();
        Assert.assertTrue(text.contains("Thank you for your submission!"));


    }
}
