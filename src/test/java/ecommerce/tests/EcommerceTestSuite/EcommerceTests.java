package ecommerce.tests.EcommerceTestSuite;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class EcommerceTests {
    private WebDriver driver;

    // Intializing ChromeBrowser
    @BeforeClass
    public void setUp() {
        // Set path to your ChromeDriver 
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //Registration Functionality
    @Test(priority = 1)
    public void testUserRegistration() {
        driver.get("https://www.flipkart.com/login");
        
     // Click on New to FlipKart to register an account
        WebElement signUpLink = driver.findElement(By.xpath("//a[contains(text(),'New to Flipkart? Sign up')]"));
        signUpLink.click();
        
        // Enter registration details
        WebElement mobileNumberField = driver.findElement(By.xpath("//input[@type='text']"));
        mobileNumberField.sendKeys("your-mobile-number");
        
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.sendKeys("your-password");
        
        // Click on "Sign up"
        WebElement signUpButton = driver.findElement(By.xpath("//button[@type='submit']"));
        signUpButton.click();
        
        // Verify registration success
        WebElement registrationSuccessMessage = driver.findElement(By.xpath("//div[contains(text(),'Registration successful')]"));
        Assert.assertTrue(registrationSuccessMessage.isDisplayed(), "Registration failed");
    }

    //Login Functionality
    @Test(priority = 2)
    public void testLoginFunctionality() {
    	//Enter Login Details
    	driver.get("https://www.flipkart.com/login");
        WebElement emailField = driver.findElement(By.xpath("//input[@type='text']"));
        emailField.sendKeys("your-email");
        
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.sendKeys("your-password");
        //Click LoginButton
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
        
        WebElement accountLink = driver.findElement(By.xpath("//span[contains(text(),'My Account')]"));
        Assert.assertTrue(accountLink.isDisplayed(), "Login failed");
    }

    //Searching Product Functionality
    @Test(priority = 3)
    public void testProductSearch() {
    	driver.get("https://www.flipkart.com");
    	//Search the product
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("smartphones");
        searchBox.sendKeys(Keys.ENTER);
        // Verify search results page loaded, check results container is displayed and has at least one product
        WebElement searchResult = driver.findElement(By.xpath("//span[contains(text(),'Showing results for')]"));
        Assert.assertTrue(searchResult.isDisplayed(), "Search resultsnot displayed.");
    }
 
    //ADD TO CART and Product Checkout Functionality
    @Test(priority = 4)
    public void testCheckoutProcess() {
    	driver.get("https://www.flipkart.com/product-page");
    	// Add product to cart
        WebElement addToCartButton = driver.findElement(By.xpath("//button[contains(text(),'Add to Cart')]"));
        addToCartButton.click();
        // Proceed to checkout
        driver.get("https://www.flipkart.com/cart");
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[contains(text(),'Place Order')]"));
        placeOrderButton.click();
        // Verify order confirmation
        WebElement orderConfirmationMessage = driver.findElement(By.xpath("//div[contains(text(),'Order placed successfully')]"));
        Assert.assertTrue(orderConfirmationMessage.isDisplayed(), "Order not placed successfully");
    }

    //Closing ChromeBrowser and Driver 
  @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
