import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static io.qameta.allure.Allure.addAttachment;

public class Authorization {

    private WebDriver driver;
    SafetyPage safetyPage;
    LoginPage loginPage;
    MainPage mainPage;
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriver.Timeouts timeouts = driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        timeouts.implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://frontend.test2.infin.money/");

    }

    @Test
    public void validAuthorization() {
        safetyPage = new SafetyPage(driver);
        loginPage = new LoginPage(driver);
        safetyPage.unsafeJoin();
        mainPage = loginPage.singIn();
        boolean check = mainPage.checkAutoRization();
        Assert.assertTrue(check);
    }

    @Test
    public void incorrectPassword() {
        safetyPage = new SafetyPage(driver);
        loginPage = new LoginPage(driver);
        safetyPage.unsafeJoin();
        Boolean check = loginPage.falsePassword();
        Assert.assertTrue(check);
    }

    @Test
    public void emptyFields() {
        safetyPage = new SafetyPage(driver);
        loginPage = new LoginPage(driver);
        safetyPage.unsafeJoin();
        Boolean check = loginPage.emptyFields();
        Assert.assertTrue(check);
    }

    @Test
    public void emptyPassword() {
        safetyPage = new SafetyPage(driver);
        loginPage = new LoginPage(driver);
        safetyPage.unsafeJoin();
        Boolean check = loginPage.emptyPassword();
        Assert.assertTrue(check);
    }


    @AfterMethod
    public void takeScreenshot(ITestResult result) {
        if (! result.isSuccess()) {
            File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("hh_mm_ss");
            String name = format.format(date)+".png";

            try {
                FileUtils.copyFile(screen,new File("/home/evgeniy/Pictures/infin/"+name));
                addAttachment("Screenshot", FileUtils.openInputStream(screen));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        driver.quit();
    }

}
