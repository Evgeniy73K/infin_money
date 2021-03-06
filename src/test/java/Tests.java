import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.audits.model.SameSiteCookieOperation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
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
        Assert.assertEquals("???????????? ???????????????????? ???????????? ??????????????", message);

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
        Assert.assertEquals(error, "???????? ???? ???????????? ???????? ????????????");
    }

    @Test(priority = 5)
    public void checkValidationBikBankField() {
        safetyPage.unsafeJoin();
        loginPage.singIn();
        mainPage.clickCheckPage();
        String errorM = checkPage.createNewCheck("sadsa", "12");
        Assert.assertEquals(errorM, "?????????? ?????? ?????????? ???????????? ???????? 10 ????????????????");
    }

    @Test
    public void checkEdittingProfile() throws InterruptedException {
        safetyPage.unsafeJoin();
        loginPage.singIn();
        mainPage.clickProfile();
        String nameInput = profilePage.editName("?????????????? ????????DD");
        String nameActual = profilePage.getName();
        System.out.println(nameInput);
        System.out.println(nameActual);
        Assert.assertEquals(nameInput, nameActual);

    }

    @Test
    public void cancelEvent () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        safetyPage.unsafeJoin();
        loginPage.singIn();
        mainPage.clickSettingButton();
        settingsPage.sendEvent();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class=\"sc-bdvvtL kIEjym inviteLoader blockLoader\"]")));
        settingsPage.cancel();
        Boolean isPresent = driver.findElements(By.xpath("//div[text()=\"test@gmail.com\"]")).size() > 0;
        Assert.assertTrue(!isPresent);



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
