import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SettingsPage {
    private WebDriver driver;

    public SettingsPage(WebDriver driver) {
        this.driver = driver;
    }
    private By importButton = By.xpath("//div[@class=\"sc-djWRfJ kjnBPq sc-bUbRBg flRwdd\"]//div[text()=\"Импорт\"]");
    private By input = By.xpath("//input");
    private By selectData = By.xpath("//div[text()=\"Определить значение\"]");
    private By selectDataValue = By.xpath("//div[text()=\"Дата\"]");
    private By selectDataSum = By.xpath("//div[text()=\"Сумма\"]");
    private By selectDataArticle = By.xpath("//div[text()=\"Статья\"]");
    private By selectDataAgent = By.xpath("//div[text()=\"Контрагент\"]");
    private By selectDataNav = By.xpath("//div[text()=\"Направление\"]");
    private By selectDataCheck = By.xpath("//div[text()=\"Счёт\"]");
    private By SendImportButton = By.xpath("//button[@class=\"sc-pVTFL bAhCI rectButton  \"]");
    private By confirmImportButton = By.xpath("//button[text()=\"Заполнить значения\"]");
    private By checkImport = By.xpath("//div[text()=\"Импорт транзакций прошел успешно\"]");
    private By calaboratorsButton = By.xpath("//div[text()=\"Сотрудники\"]");
    private By  eventCalButton = By.xpath("//button[text()=\"Пригласить сотрудника\"]");
    private By emailField = By.xpath("//input[@name=\"email\"]");
    public void clickImportButton() {
        driver.findElement(importButton).click();
    }

    public void sendFile () {
        driver.findElement(input).sendKeys("/home/evgeniy/Downloads/import-template.xlsx");
        driver.findElement(selectData).click();
        driver.findElement(selectDataValue).click();
        driver.findElement(selectData).click();
        driver.findElement(selectDataSum).click();
        driver.findElement(selectData).click();
        driver.findElement(selectDataArticle).click();
        driver.findElement(selectData).click();
        driver.findElement(selectDataAgent).click();
        driver.findElement(selectData).click();
        driver.findElement(selectDataNav).click();
        driver.findElement(selectData).click();
        driver.findElement(selectDataCheck).click();
        driver.findElement(SendImportButton).click();
        driver.findElement(confirmImportButton).click();
    }

    public String checkMessage() throws InterruptedException {
        Thread.sleep(100);
        String message = driver.findElement(checkImport).getText();
        return message;
    }

    public void sendEvent () {
        driver.findElement(calaboratorsButton).click();
        driver.findElement(eventCalButton).click();
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys("test@google.com");
        driver.findElement(emailField).submit();

    }
}
