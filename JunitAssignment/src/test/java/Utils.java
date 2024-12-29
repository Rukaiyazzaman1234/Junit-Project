import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class Utils {
    public static void scroll(WebDriver driver, int height) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + height + ")"); // scroll code


    }

    public static CharSequence generatePhoneNumber() {
        int min = 1000000;
        int max = 9999999;
        int concat = (int) (Math.random() * (max - min) + min);
        return "996" + concat;
    }


}
