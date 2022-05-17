import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckPage {
    WebDriver driver;

    public CheckPage(WebDriver driver) {
        this.driver = driver;
    }

    private By fieldName = By.xpath("//input[@id=\"rRHgmHPklwugMKd03uVUS\"]");
    private By fieldBalance = By.xpath("//input[@id=\"NRp0Ye5yDJn8yqOugOWhz\"]");
    private By selector = By.xpath("//div[text()=\"Выберите категорию\"]");
    private By selectorData = By.xpath("//div[text()=\"Безнал\"]");
    private By titleBank = By.xpath("//input[@name=\"titleBank\"]");
    private By bikBank = By.xpath("//input[@name=\"bikBank\"]");
    private By invoiceNumber = By.xpath("//input[@name=\"bikBank\"]");
    private By direction = By.xpath("//div[@class=\"undefined itemText\"]");

    public void createNewCheck() {
        driver.findElement(fieldName).sendKeys("Test");
        driver.findElement(fieldBalance).sendKeys("100");
        driver.findElement(selector).click();
        driver.findElement(selectorData).click();
        driver.findElement(titleBank).sendKeys("Test bank");
        driver.findElement(bikBank).sendKeys("123456");
        driver.findElement(invoiceNumber).sendKeys("123456");
        driver.findElement(selector).click();
        driver.findElement(direction).click();

    }

}
