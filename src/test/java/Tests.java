import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.audits.model.SameSiteCookieOperation;
import org.testng.Assert;
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

public class Tests {

    private WebDriver driver;
    private SafetyPage safetyPage;
    private LoginPage loginPage;
    private MainPage mainPage;
    private NewSalePage newSalePage;
    private SettingsPage settingsPage;
    private  CheckPage checkPage;
    private ProfilePage profilePage;


    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriver.Timeouts timeouts = driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        timeouts.implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://frontend.test2.infin.money/");
        safetyPage = new SafetyPage(driver);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        newSalePage = new NewSalePage(driver);
        settingsPage = new SettingsPage(driver);
        checkPage = new CheckPage(driver);
        profilePage = new ProfilePage(driver);
    }

    @Test(priority = 1)
    public void transactionRecovery() {
        safetyPage.unsafeJoin();
        loginPage.singIn();
        mainPage.clickSaleslink();
        mainPage.clickAddButton();
        newSalePage.addNew();
        newSalePage.clickItem();
        newSalePage.deleteSales();
        newSalePage.re();
        Boolean check = newSalePage.check();
        Assert.assertTrue(check);


    }

    @Test(priority = 2)
    public void importFile () throws InterruptedException {
        safetyPage.unsafeJoin();
        loginPage.singIn();
        mainPage.clickSettingButton();
        settingsPage.clickImportButton();
        settingsPage.sendFile();
        String message = settingsPage.checkMessage();
        Assert.assertEquals("Импорт транзакций прошел успешно", message);

    }

    @Test(priority = 3)
    public void createCheck() {
        safetyPage.unsafeJoin();
        loginPage.singIn();
        mainPage.clickCheckPage();
        String name = checkPage.createNewCheck("Test", "044525974");
        String bankName = checkPage.getBankName(name);
        System.out.println(name);
        System.out.println(bankName);
        Assert.assertEquals(name, bankName);

    }

    @Test(priority = 4)
    public void checkValidationCheckField() {
        safetyPage.unsafeJoin();
        loginPage.singIn();
        mainPage.clickCheckPage();
        String  error = checkPage.createNewCheck();
        Assert.assertEquals(error, "Поле не должно быть пустым");
    }

    @Test(priority = 5)
    public void checkValidationBikBankField() {
        safetyPage.unsafeJoin();
        loginPage.singIn();
        mainPage.clickCheckPage();
        String errorM = checkPage.createNewCheck("sadsa", "12");
        Assert.assertEquals(errorM, "Длина БИК банка должна быть 10 символов");
    }

    @Test(priority = 0)
    public void checkAutorization() {
        safetyPage.unsafeJoin();
        loginPage.singIn();
        Boolean check = mainPage.checkAutoRization();
        Assert.assertTrue(check);
    }

    @Test
    public void checkEdittingProfile() throws InterruptedException {
        safetyPage.unsafeJoin();
        loginPage.singIn();
        mainPage.clickProfile();
        String nameInput = profilePage.editName("Василий СольDD");
        String nameActual = profilePage.getName();
        System.out.println(nameInput);
        System.out.println(nameActual);
        Assert.assertEquals(nameInput, nameActual);

    }

    @Test
    public void cancelEvent () {
        safetyPage.unsafeJoin();
        loginPage.singIn();
        mainPage.clickSettingButton();
        settingsPage.sendEvent();

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
