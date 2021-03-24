import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class OnlyImplicitWait {

    private WebDriver driver;

    @BeforeMethod
    public void beforeTestMethod() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //устанавливаем implicitlyWait в 10 секунд
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //тест должен ждать 10 секунд, прежде чем упасть

    @Test
    public void implicitWaitTest() {
        driver.get("https://novaposhta.ua/");
        //выводим на экран текущее время (точка отсчета)
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        // пробуем найти элемент по локатору, которого не существует
        driver.findElement(By.xpath("//NOT_EXISTED_XPATH"));
    }

    @AfterMethod
    public void afterTestMethod() {
        //опять выводим на экран текущее время, что бы видеть сколько времени selenium ждал
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        driver.quit();
    }


}
