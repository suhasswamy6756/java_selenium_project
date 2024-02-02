package ecommerce.src;

import java.io.IOException;
import java.time.Duration;

import java.util.Set;
import java.io.FileReader;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class project4 {
    public static void main(String[] args) throws InterruptedException, CsvValidationException {
        // System.setProperty("webdriver.edge.driver", "C:\\Users\\suhas\\Desktop\\Academics\\5th sem\\software tesitng\\project\\ecommerce\\src\\driver\\msedgedriver.exe");

        Thread thread1 = new Thread(() -> testWebsite(new ChromeDriver()));
        Thread thread2 = new Thread(() -> testWebsite(new EdgeDriver()));

        // Start the threads
        thread1.start();
        thread2.start();

        // Wait for both threads to finish
        thread1.join();
        thread2.join();
        
        
    }
    public static void testWebsite(WebDriver driver) {
        try {
            // Your existing code here

            // Example: Launch Chrome and test
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\suhas\\Desktop\\Academics\\5th sem\\software tesitng\\project\\ecommerce\\src\\driver\\chromedriver.exe");
            WebDriver chromeDriver = new ChromeDriver();
            testWebsite(chromeDriver);

            // Example: Launch Firefox and test
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\suhas\\Desktop\\Academics\\5th sem\\software tesitng\\project\\ecommerce\\src\\driver\\msedgedriver.exe");
            WebDriver firefoxDriver = new FirefoxDriver();
            testWebsite(firefoxDriver);
        } finally {
            // Close the driver when done
            driver.quit();
        }
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

  

    public static void dataDrivenTest(WebDriver driver) throws CsvValidationException, InterruptedException {
        try {
            // Open the login page
            driver.get("https://www.google.com");
            driver.findElement(By.name("q")).sendKeys("https://demoqa.com/login/");
            driver.findElement(By.name("q")).submit();
            driver.navigate().to("https://demoqa.com/login");
    
            // Define the path to your CSV file
            String csvFilePath = "C:\\Users\\suhas\\Desktop\\Academics\\5th sem\\software tesitng\\project\\ecommerce\\src\\CSVFile\\testdata.csv";
    
            // Create a CSVReader
            try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
                String[] nextLine;
    
                // Loop through each row in the CSV file
                while ((nextLine = reader.readNext()) != null) {
                    // Find username and password elements
                    WebElement usernameInput = driver.findElement(By.id("userName"));
                    WebElement passwordInput = driver.findElement(By.id("password"));
    
                    // Clear existing values in the inputs
                    usernameInput.clear();
                    passwordInput.clear();
    
                    // Enter username and password from the current row in the CSV file
                    usernameInput.sendKeys(nextLine[0]);
                    passwordInput.sendKeys(nextLine[1]);
                    scrollDown(driver, 500);
                    Thread.sleep(2000);
    
                    WebElement loginbtn = driver.findElement(By.id("login"));
                    loginbtn.click();
                    System.out.println(nextLine[0] + " login successfully");
    
                    // Wait for a moment before moving to the next set of data
                    Thread.sleep(2000);
    
                    WebElement loginbtn1 = driver.findElement(By.id("submit"));
                    loginbtn1.click();
                    System.out.println(nextLine[0] + " sign out successfully");
    
                    Thread.sleep(2000);
                }
            }
    
        } catch (IOException e) {
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


