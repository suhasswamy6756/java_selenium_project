import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionsWithCredentials {

    public static void main(String[] args) throws InterruptedException {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\suhas\\Desktop\\Academics\\5th sem\\software tesitng\\project\\ecommerce\\src\\driver\\chromedriver.exe");

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Create an instance of the Actions class
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

        } finally {
            // Close the browser
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
