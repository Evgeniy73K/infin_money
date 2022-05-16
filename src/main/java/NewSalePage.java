
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class NewSalePage {
    private WebDriver driver;

    public NewSalePage(WebDriver driver) {
        this.driver = driver;
    }

    private By totalField = By.xpath("//input[@name=\"total\"]");
    private By selectDirection = By.xpath("//div[text()=\"Выберите направление\"]");
    private By selectArticle = By.xpath("//div[text()=\"Выберите статью\"]");
    private By selectCheck = By.xpath("//div[text()=\"Выберите счет\"]");
    private By addButton = By.xpath("//button[text()=\"Добавить продажу\"]");
    private By selector = By.xpath("//div[@class=\"undefined itemText\"]");
    private By itemButton = By.xpath("//button[@class=\"sc-pVTFL bxJNrM rectButton iconButton \"]");
    private By deleteButton = By.xpath("//button[text()=\"Удалить\"]");
    private By confirmDeleteButton = By.xpath("//button[@class=\"sc-pVTFL czfJEt rectButton  \"]");
    private By activeButton = By.xpath("//div[text()=\"Активные\"]");
    private By reButton = By.xpath("//button[text()=\"Восстановить\"]");
    private By price = By.xpath("//div[@class=\"dataItemValue totalText\"]");


    public void addNew () {
        driver.findElement(totalField).sendKeys("100");
        driver.findElement(selectDirection).click();
        driver.findElement(selector).click();
        driver.findElement(selectArticle).click();
        driver.findElement(selector).click();
        driver.findElement(selectCheck).click();
        driver.findElement(selector).click();
        driver.findElement(addButton).click();
    }

    public void clickItem () {
        driver.findElement(activeButton).click();
        driver.findElement(itemButton).click();
    }

    public void deleteSales() {
        driver.findElement(deleteButton).click();
        driver.findElement(confirmDeleteButton).click();

    }

    public void re() {
        driver.findElement(reButton).click();
    }

    public Boolean check() {
        if (driver.findElement(price).isDisplayed()) {
            return true;
        }
        else {
            return false;
        }
    }



}
