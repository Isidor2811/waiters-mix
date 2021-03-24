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

public class OnlyExplicitWait {

    private WebDriver driver;

    @BeforeMethod
    public void beforeTestMethod() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }


    //тест должен ждать 17 секунд, прежде чем упасть

    @Test
    public void explicitWaitTest() {
        driver.get("https://novaposhta.ua/");
        //выводим на экран текущее время (точка отсчета)
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        //устанавливаем время ожидания пока элемент станет видимым в 17 секунд
        new WebDriverWait(driver, 17)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//NOT_EXISTED_XPATH")));
    }

    @AfterMethod
    public void afterTestMethod() {
        //опять выводим на экран текущее время, что бы видеть сколько времени selenium ждал
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        driver.quit();
    }


}
