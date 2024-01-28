import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;

public class DataDrivenTest {

    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\suhas\\Desktop\\Academics\\5th sem\\software tesitng\\project\\ecommerce\\src\\driver\\chromedriver.exe");

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

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
}
