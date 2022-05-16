import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By emailField = By.name("email");
    private By passwordField = By.name("password");
    private By singInButton = By.xpath("//button[@class=\"sc-pVTFL bxJNrM button loginButton rectButton  \"]");

    public MainPage singIn() {
        driver.findElement(emailField).sendKeys("e.karabaev@mediasoft.team");
        driver.findElement(passwordField).sendKeys("MEDIA777");
        driver.findElement(singInButton).click();
        return new MainPage(driver);
    }

}
