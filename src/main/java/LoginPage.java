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
    private By invalidPassword = By.xpath("//div[text()=\"Неверный пароль.\"]");
    private By emailError = By.xpath("//div[@class=\"sc-bkkeKt kgPbWz textField emailField error\"]");
    private By passwordError = By.xpath("//div[@class=\"sc-bkkeKt XAgpD textField passField error\"]");


    public MainPage singIn() {
        driver.findElement(emailField).sendKeys("e.karabaev@mediasoft.team");
        driver.findElement(passwordField).sendKeys("MEDIA777");
        driver.findElement(singInButton).click();
        return new MainPage(driver);
    }

    public Boolean falsePassword() {
        driver.findElement(emailField).sendKeys("e.karabaev@mediasoft.team");
        driver.findElement(passwordField).sendKeys("ME");
        driver.findElement(singInButton).click();
        if (driver.findElement(invalidPassword).isEnabled()) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean emptyFields() {
        driver.findElement(emailField).sendKeys("");
        driver.findElement(passwordField).sendKeys("");
        driver.findElement(singInButton).click();
        if (driver.findElement(emailError).isEnabled() && driver.findElement(passwordError).isEnabled()) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean emptyPassword() {
        driver.findElement(emailField).sendKeys("e.karabaev@mediasoft.team");
        driver.findElement(passwordField).sendKeys("");
        driver.findElement(singInButton).click();
        if (driver.findElement(passwordError).isEnabled()) {
            return true;
        } else {
            return false;
        }
    }
}
