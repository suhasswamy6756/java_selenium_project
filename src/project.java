import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class project {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\suhas\\Desktop\\Academics\\5th sem\\software tesitng\\project\\ecommerce\\src\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        // WebDriverWait wait = new WebDriverWait(driver, 3);
        
        

        cookies(driver);
        Thread.sleep(2000);
        data_driven_test(driver);
        Thread.sleep(2000);
        copy_paste(driver);

        

        Thread.sleep(10000);
        driver.quit();
        
        
    }
    public static void cookies(WebDriver driver){

        driver.get("https://www.google.com");
        driver.findElement(By.name("q")).sendKeys("https://demoqa.com/register/");
        driver.findElement(By.name("q")).submit();
        driver.navigate().to("https://demoqa.com/register");
        Set<Cookie> allCookies = driver.manage().getCookies();
        System.out.println("All Cookies: " + allCookies);

        // Add a new cookie
        Cookie newCookie = new Cookie("exampleCookie", "cookieValue");
        driver.manage().addCookie(newCookie);

        // Get and display the value of the added cookie
        Cookie addedCookie = driver.manage().getCookieNamed("exampleCookie");
        System.out.println("Added Cookie Value: " + addedCookie.getValue());

        // Delete the added cookie
        driver.manage().deleteCookie(addedCookie);

        // Verify that the cookie is deleted
        System.out.println("Is Cookie Deleted: " + !driver.manage().getCookies().contains(addedCookie));

        // Delete all cookies
        driver.manage().deleteAllCookies();
        System.out.println("All Cookies after deletion: " + driver.manage().getCookies());
    }

    public static void data_driven_test(WebDriver driver){
         try {
            // Open the login page
            driver.get("https://www.google.com");
            driver.findElement(By.name("q")).sendKeys("https://demoqa.com/login/");
            driver.findElement(By.name("q")).submit();
            driver.navigate().to("https://demoqa.com/login");

            // Define the data sets (username, password)
            List<String[]> testData = Arrays.asList(
                    new String[]{"suppi", "Suhas@suppi9060"},
                    new String[]{"abdul", "Abdul@1234"},
                    new String[]{"sudeep", "Sudeep@1234"}
                    // Add more data sets as needed
            );

            // Loop through each set of data
            for (String[] data : testData) {
                // Find username and password elements
                WebElement usernameInput = driver.findElement(By.id("userName"));
                WebElement passwordInput = driver.findElement(By.id("password"));



                // Clear existing values in the inputs
                usernameInput.clear();
                passwordInput.clear();

                // Enter username and password from the current data set
                usernameInput.sendKeys(data[0]);
                passwordInput.sendKeys(data[1]);
                scrollDown(driver, 500);
                Thread.sleep(2000);
               WebElement loginbtn =  driver.findElement(By.id("login"));
               loginbtn.click();
               System.out.println(data[0]+" login succesfully");

                // Submit the form (you may need to adjust the selector)
                // passwordInput.sendKeys(Keys.RETURN);

                // You can add verification steps or assertions here

                // Wait for a moment before moving to the next set of data
                // driver.navigate().to("https://demoqa.com/profile");
                Thread.sleep(2000);

                WebElement loginbtn1=  driver.findElement(By.id("submit"));
               loginbtn1.click();
               System.out.println(data[0]+" sign out succesfully");
               
               Thread.sleep(2000);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
    }
    private static void scrollDown(WebDriver driver, int pixels) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, " + pixels + ");");
    }
    public static void copy_paste(WebDriver driver){
        Actions actions = new Actions(driver);

        // Create a WebDriverWait with a timeout of 10 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Open a website
            driver.get("https://www.google.com");
            driver.findElement(By.name("q")).sendKeys("https://demoqa.com/register/");
            driver.findElement(By.name("q")).submit();
            driver.navigate().to("https://demoqa.com/register");

            // Locate the source and target elements for copy-paste
            WebElement sourceElement = driver.findElement(By.id("firstname"));
            WebElement targetElement = driver.findElement(By.id("lastname"));

            sourceElement.sendKeys("suhas");

            // Perform a double-click to select the entire text in the source element
            actions.doubleClick(sourceElement).perform();

            // Perform a copy operation (Ctrl+C) using keyboard shortcuts
            actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();

            // Wait for the target element to be clickable
            wait.until(ExpectedConditions.elementToBeClickable(targetElement));
            Thread.sleep(2000);
            // Move to the target element and perform a paste operation (Ctrl+V)
            actions.moveToElement(targetElement).click().perform();
            actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();

            Thread.sleep(10000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
    }
    
}

