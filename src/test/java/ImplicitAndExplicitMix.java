import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class ImplicitAndExplicitMix {

    private WebDriver driver;

    @BeforeMethod
    public void beforeTestMethod() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //устанавливаем implicitlyWait в 10 секунд
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //нельзя делать два вейтера в одном тесте!!!

    @Test
    public void explicitWaitTest() {
        driver.get("https://novaposhta.ua/");
        //выводим на экран текущее время (точка отсчета)
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        //устанавливаем время ожидания пока элемент станет видимым в 12 секунд
        new WebDriverWait(driver, 12)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//NOT_EXISTED_XPATH")));
    }

    @AfterMethod
    public void afterTestMethod() {
        //опять выводим на экран текущее время, что бы видеть сколько времени selenium ждал
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        driver.quit();
    }


    /*
    Время которое мы получим будет примерно таким:
    start - 12:45:48
    end - 12:46:05
    разница выходит в 17 секунд, хотя ожидание implicitlyWait было 10, а explicitWait - 12 секунд
*/
}
