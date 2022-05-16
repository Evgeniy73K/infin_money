import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Tests {

    private WebDriver driver;
    private SafetyPage safetyPage;
    private LoginPage loginPage;
    private MainPage mainPage;
    private NewSalePage newSalePage;
    private SettingsPage settingsPage;


    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriver.Timeouts timeouts = driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://frontend.test2.infin.money/");
        safetyPage = new SafetyPage(driver);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        newSalePage = new NewSalePage(driver);
        settingsPage = new SettingsPage(driver);
    }

   /* @Test
    public void transactionRecovery() {
        safetyPage.unsafeJoin();
        loginPage.singIn();
        mainPage.clickSaleslink();
        mainPage.clickAddButton();
        newSalePage.addNew();
        newSalePage.clickItem();
        newSalePage.deleteSales();
        newSalePage.re();
        newSalePage.check();




    }*/

    @Test

    public void importFile () {
        safetyPage.unsafeJoin();
        loginPage.singIn();
        mainPage.clickSettingButton();
        settingsPage.clickImportButton();
        settingsPage.sentFile();

    }


    @AfterMethod
    public void tearDown() {
       // driver.quit();
    }
}
