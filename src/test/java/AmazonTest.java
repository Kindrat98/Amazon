import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmazonTest {

    private static final String AMAZON_MAIN_URL = "https://www.amazon.com/";

    private WebDriver driver;
    private HeaderPage headerPage;
    private ProductsListPage productsListPage;
    private ProductPage productPage;

    @BeforeSuite
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
    }

    @BeforeMethod
    public void setupPages() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        headerPage = new HeaderPage(driver);
        productsListPage = new ProductsListPage(driver);
        productPage = new ProductPage(driver);
        driver.get(AMAZON_MAIN_URL);
    }

    @AfterMethod
    public void quit() {
        driver.quit();
        driver = null;
    }

    @Test
    public void test1() {
        String brand = "SAMSUNG";

        headerPage.search("phone");
        productsListPage.setCheckbox(brand);
        List<String> productNames = productsListPage.getProductNames();

        for (String goodName : productNames) {
            System.out.println(goodName);
            Assert.assertTrue(goodName.toUpperCase().contains(brand));
        }
    }

    @Test
    public void test2() {
        String mainCategory = "Electronics";
        String subCategory = "Headphones";
        String brand = "Sony";

        headerPage.selectAllProducts();
        headerPage.selectMainCategory(mainCategory);
        headerPage.selectSubCategory(subCategory);
        productsListPage.setCheckbox(brand);

        String expectedResult = productsListPage.getProductName(0);
        productsListPage.selectProduct(0);

        String actualResult = productPage.getProductTitle();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test3() {
        String mainCategory = "Electronics";
        String subCategory = "Camera & Photo";

        headerPage.selectAllProducts();
        headerPage.selectMainCategory(mainCategory);
        headerPage.selectSubCategory(subCategory);

        String actualSubCategoryPageHeaderText = productsListPage.getProductsHeaderName();

        Assert.assertEquals(actualSubCategoryPageHeaderText, subCategory);
    }

}
