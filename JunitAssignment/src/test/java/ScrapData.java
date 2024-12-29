import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ScrapData {
    WebDriver driver;

    @BeforeAll
    public void setup() {
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--headed");
        driver = new ChromeDriver(ops);
    }

    @Test
    public void scrapWebTableData() throws IOException {

        driver.get("https://dsebd.org/latest_share_price_scroll_by_value.php");
        List<WebElement> Table = driver.findElements(By.className("table"));
        List<WebElement> allRows = Table.get(1).findElements(By.tagName("tr"));

        FileWriter writer = new FileWriter("./src/test/resources/output.txt", true);

        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                String tableData =cell.getText();
                System.out.print(tableData);
                writer.write(" "+tableData+" ");
            }
            System.out.println();
            writer.write("\n");
        }
        writer.close();

    }

    @AfterAll
    public void closeDriver() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}