import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By salesLink = By.xpath("//div[@class=\"sc-gIBqdA fcBZPL sc-PZsNp dknJIW  \"]/div[text()=\"Продажи\"]");
    private By addButton = By.xpath("//button[@class=\"sc-pVTFL bxJNrM addButton rectButton  \"]");

    public void clickSaleslink() {
        driver.findElement(salesLink).click();
    }

    public NewSalePage clickAddButton() {
        driver.findElement(addButton).click();
        return new NewSalePage(driver);
    }

}
