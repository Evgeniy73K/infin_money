import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class CheckPage {
    String bankName;


    WebDriver driver;

    public CheckPage(WebDriver driver) {
        this.driver = driver;
    }

    Random rand = new Random();

    private By fieldName = By.name("title");
    private By fieldBalance = By.name("balance");
    private By selector = By.xpath("//div[text()=\"Выберите категорию\"]");
    private By selector2 = By.xpath("//div[text()=\"Выберите направление\"]");
    private By selectorData = By.xpath("//li[@class=\"dropdownItem   undefined\"]");
    private By test = By.xpath("//ul[@class=\"sc-dJjYzT jhQteg undefined\"]/li");
    private By titleBank = By.xpath("//input[@name=\"titleBank\"]");
    private By bikBank = By.xpath("//input[@name=\"bikBank\"]");
    private By invoiceNumber = By.xpath("//input[@name=\"invoiceNumber\"]");
    private By buttonAdd = By.xpath("//button[text()=\"Добавить счет\"]");
    private By linkContent = By.xpath("//div[@class=\"linkContent\"]");
    private By error = By.xpath("//div[@class=\"errorMessage\"]");


    public String createNewCheck(String name, String bik) {
        int n = rand.nextInt(500);
        name = name +n;
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldBalance).click();
        driver.findElement(fieldBalance).clear();
        driver.findElement(fieldBalance).sendKeys("1000");
        driver.findElement(selector).click();
        driver.findElement(selectorData).click();
        driver.findElement(titleBank).sendKeys("Test bank");
        driver.findElement(bikBank).sendKeys(bik);
        driver.findElement(invoiceNumber).sendKeys("12345678901234567890");
        driver.findElement(selector2).click();
        driver.findElement(test).click();
        driver.findElement(buttonAdd).click();
        if (bik.length()<9) {
            String errorM = driver.findElement(error).getText();
            return errorM;

        } else {
            return name;
        }



    }

    public String createNewCheck() {
        driver.findElement(selector).click();
        driver.findElement(selectorData).click();
        driver.findElement(titleBank).sendKeys("Test bank");
        driver.findElement(bikBank).sendKeys("044525974");
        driver.findElement(invoiceNumber).sendKeys("12345678901234567890");
        driver.findElement(selector2).click();
        driver.findElement(test).click();
        driver.findElement(buttonAdd).click();
        String errorMessage =  driver.findElement(error).getText();
        return errorMessage;


    }



    public String getBankName(String xpath_selector) {
        By xpath = By.xpath("//div[text()=\""+xpath_selector+"\"]");
        String bankName = driver.findElement(xpath).getText();
        return  bankName;
    }

}
