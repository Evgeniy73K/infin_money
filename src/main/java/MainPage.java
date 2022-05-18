import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By salesLink = By.xpath("//div[@class=\"sc-gIBqdA fcBZPL sc-PZsNp dknJIW  \"]/div[text()=\"Продажи\"]");
    private By addButton = By.xpath("//button[@class=\"sc-pVTFL bxJNrM addButton rectButton  \"]");
    private By settingsButton = By.xpath("//div[text()=\"Настройки\"]");
    private By checkLink = By.xpath("//div[text()=\"Счета\"]");
    private By labelAuto = By.xpath("//span[@class=\"profileTitle\"]");


    public void clickSaleslink() {

        driver.findElement(salesLink).click();
    }
    public CheckPage clickCheckPage() {
        driver.findElement(checkLink).click();
        driver.findElement(addButton).click();
        return new CheckPage(driver);

    }

    public NewSalePage clickAddButton() {
        driver.findElement(addButton).click();
        return new NewSalePage(driver);
    }

    public SettingsPage clickSettingButton() {
        driver.findElement(settingsButton).click();
        return new SettingsPage(driver);
    }

    public Boolean checkAutoRization() {
        if(driver.findElement(labelAuto).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

}
