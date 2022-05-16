import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SettingsPage {
    private WebDriver driver;

    public SettingsPage(WebDriver driver) {
        this.driver = driver;
    }
    private By importButton = By.xpath("//div[@class=\"sc-djWRfJ kjnBPq sc-bUbRBg flRwdd\"]//div[text()=\"Импорт\"]");
    private By input = By.xpath("//input");
    public void clickImportButton() {
        driver.findElement(importButton).click();
    }

    public void sentFile () {
        driver.findElement(input).sendKeys("/home/evgeniy/Downloads/import-template.xlsx");
    }
}
