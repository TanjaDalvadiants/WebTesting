import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebTest {
    WebDriver driver;

    @BeforeAll
    static void setupAll() {

        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestHappyPathSelenium() {

        driver.get("http://localhost:9999/");
        driver.findElements(By.tagName("input")).get(0).sendKeys("Татьяна");
        driver.findElements(By.tagName("input")).get(1).sendKeys("+79042424072");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.className("Success_successBlock__2L3Cw")).getText().trim();
        assertEquals(expected, actual);
    }
    @Test
    void shouldTestLatinNameSelenium() {

        driver.get("http://localhost:9999/");
        driver.findElements(By.tagName("input")).get(0).sendKeys("Tatyana");
        driver.findElements(By.tagName("input")).get(1).sendKeys("+79042424072");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.className("input__sub")).getText().trim();
        assertEquals(expected, actual);
    }
    @Test
    void shouldTestNameWithNumbersSelenium() {

        driver.get("http://localhost:9999/");
        driver.findElements(By.tagName("input")).get(0).sendKeys("Татьяна1");
        driver.findElements(By.tagName("input")).get(1).sendKeys("+79042424072");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.className("input__sub")).getText().trim();
        assertEquals(expected, actual);
    }
    @Test
    void shouldTestEmptyNameSelenium() {

        driver.get("http://localhost:9999/");
        driver.findElements(By.tagName("input")).get(0).sendKeys(" ");
        driver.findElements(By.tagName("input")).get(1).sendKeys("+79042424072");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.className("input__sub")).getText().trim();
        assertEquals(expected, actual);
    }
    @Test
    void shouldTestLongPhoneSelenium() {

        driver.get("http://localhost:9999/");
        driver.findElements(By.tagName("input")).get(0).sendKeys("Татьяна");
        driver.findElements(By.tagName("input")).get(1).sendKeys("834329423874982749287");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.xpath("/html/body/div/div/form/div[2]/span/span/span[3]")).getText().trim();
        assertEquals(expected, actual);
    }
    @Test
    void shouldTestShortPhoneSelenium() {

        driver.get("http://localhost:9999/");
        driver.findElements(By.tagName("input")).get(0).sendKeys("Татьяна");
        driver.findElements(By.tagName("input")).get(1).sendKeys("8343");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.xpath("/html/body/div/div/form/div[2]/span/span/span[3]")).getText().trim();
        assertEquals(expected, actual);
    }
    @Test
    void shouldTestEmptyPhoneSelenium() {

        driver.get("http://localhost:9999/");
        driver.findElements(By.tagName("input")).get(0).sendKeys("Татьяна");
        driver.findElements(By.tagName("input")).get(1).sendKeys(" ");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.xpath("/html/body/div/div/form/div[2]/span/span/span[3]")).getText().trim();
        assertEquals(expected, actual);
    }
    @Test
    void shouldTestAgreementNotCheckSelenium() {

        driver.get("http://localhost:9999/");
        driver.findElements(By.tagName("input")).get(0).sendKeys("Татьяна");
        driver.findElements(By.tagName("input")).get(1).sendKeys("+79042424072");
        driver.findElement(By.tagName("button")).click();

        String expected = "Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй";
        String actual = driver.findElement(By.cssSelector("[data-test-id='agreement']")).getText().trim();
        assertEquals(expected, actual);
    }


}
