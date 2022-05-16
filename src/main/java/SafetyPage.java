import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SafetyPage {
    private WebDriver driver;

    public SafetyPage(WebDriver driver) {
        this.driver = driver;
    }

    private By advanedButton = By.id("details-button");
    private By proceedLink = By.id("proceed-link");

    public LoginPage unsafeJoin () {
        driver.findElement(advanedButton).click();
        driver.findElement(proceedLink).click();
        return new LoginPage(driver);
    }

}
