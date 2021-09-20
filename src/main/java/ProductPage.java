import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage{

    private static final By PRODUCT_TITLE = By.xpath("//h1[@id='title']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductTitle(){
        return driver.findElement(PRODUCT_TITLE).getText();
    }
}
