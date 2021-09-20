import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeaderPage extends BasePage{

    private static final String MAIN_CATEGORY_PATTERN = "//div[text()='%s']";
    private static final String SUB_CATEGORY_PATTERN = "//a[text()='%s']";

    private static final By SEARCH_INPUT_LOCATOR = By.xpath("//input[@id='twotabsearchtextbox']");
    private static final By All_PRODUCTS_LOCATOR = By.xpath("//a[@id='nav-hamburger-menu']");

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    public void search(String text){
        WebElement searchInput = driver.findElement(SEARCH_INPUT_LOCATOR);
        searchInput.sendKeys(text, Keys.ENTER);
    }

    public void selectAllProducts(){
        driver.findElement(All_PRODUCTS_LOCATOR).click();
    }

    public void selectMainCategory(String mainCategory) {
        driver.findElement(By.xpath(String.format(MAIN_CATEGORY_PATTERN, mainCategory))).click();
    }

    public void selectSubCategory(String subCategory) {
        driver.findElement(By.xpath(String.format(SUB_CATEGORY_PATTERN, subCategory))).click();
    }
}
