import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {

    private WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    By nameField = By.xpath("//input[@name=\"fullName\"]");
    By emailField = By.xpath("//input[@name=\"email\"]");
    By passwordField = By.xpath("//input[@name=\"password\"]");
    By titleProfile = By.xpath("//span[@class=\"profileTitle\"]");
    By saveButton = By.xpath("//button[@class=\"sc-pVTFL bAhCI rectButton  \"]");

    public String editName(String name){
        driver.findElement(nameField).click();
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(saveButton).click();
        return name;

    }

    public String getName() throws InterruptedException {
        Thread.sleep(1000);
        String name = driver.findElement(titleProfile).getText();
        return name;
    }

}
