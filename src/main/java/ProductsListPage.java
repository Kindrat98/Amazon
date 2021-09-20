import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductsListPage extends BasePage{

    private static final String FILTER_CHECKBOX_PATTERN = "//li[@id='p_89/%s']/span";

    private static final By PRODUCTS_NAMES_LOCATOR = By.xpath("//div[@data-asin]//h2//span[contains(@class,'a-color-base')]");
    private static final By PRODUCTS_HEADER = By.xpath("//div[@id='departments']/ul/li[1]");

//    Checkbox = driver.findElement(By.xpath("//li[@id='p_89/SAMSUNG']/span"));

    public ProductsListPage(WebDriver driver) {
        super(driver);
    }

    public void setCheckbox(String filterCriteria){
        driver.findElement(
                By.xpath(String.format(FILTER_CHECKBOX_PATTERN, filterCriteria)))
                .click();
        sleep(2);
    }

    public List<String> getProductNames(){
        List<String> productNames = new ArrayList<>();
        for (WebElement productNameElement :driver.findElements(PRODUCTS_NAMES_LOCATOR)) {
            productNames.add(productNameElement.getText());
        }
        return productNames;
    }

    public void selectProduct(int index){
        driver.findElements(PRODUCTS_NAMES_LOCATOR).get(index).click();
    }

    public String getProductName(int index){
        return driver.findElements(PRODUCTS_NAMES_LOCATOR).get(index).getText();
    }

    public String getProductsHeaderName() {
        return driver.findElement(PRODUCTS_HEADER).getText();
    }
}
