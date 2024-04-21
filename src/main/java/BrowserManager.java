import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BrowserManager {
    private static WebDriver driver;
    static RegistrationPage registerPage;

    public static final String TEST_RESOURCES_DIR = "src\\test\\resources\\";
    public static final String DOWNLOAD_DIR = TEST_RESOURCES_DIR.concat("download\\");
    public static final String SCREENSHOTS_DIR = TEST_RESOURCES_DIR.concat("screenshots\\");
    public static final String REPORTS_DIR = TEST_RESOURCES_DIR.concat("reports\\");

    protected final void setupTestClass() throws IOException {
        cleanDirectory(SCREENSHOTS_DIR);
        cleanDirectory(REPORTS_DIR);
        registerPage = new RegistrationPage(getDriver());
    }

    static void initializeDriver() {
        WebDriverManager.chromedriver().setup();
        BrowserManager browserManager = new BrowserManager();
        ChromeOptions options = browserManager.configChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void deleteDownloadFiles() throws IOException {
        cleanDirectory(DOWNLOAD_DIR);
        quitDriver();
    }

    public static void closeCurrentWindow() {
        if (driver != null) {
            driver.close();
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    private ChromeOptions configChromeOptions() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", System.getProperty("user.dir").concat("\\").concat(DOWNLOAD_DIR));

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments("disable-popup-blocking");

        return chromeOptions;
    }


    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public void cleanDirectory(String directoryPath) throws IOException {
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        Assert.assertTrue(directory.isDirectory());

        FileUtils.cleanDirectory(directory);
        String[] fileList = directory.list();
        if (fileList != null && fileList.length == 0){
            System.out.printf("All file are deleted in Directory: %s%n", directoryPath);
        }else {
            System.out.printf("Unable to delete the files in Directory: %s%n", directoryPath);
        }
    }

    void takeScreenshot(ITestResult testResult) {
        if(ITestResult.FAILURE == testResult.getStatus()){
            try{
                TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
                File screenshots = takesScreenshot.getScreenshotAs(OutputType.FILE);
                String testName = testResult.getName();
                FileUtils.copyFile(screenshots, new File(SCREENSHOTS_DIR.concat(testName).concat(".jpg")));
            }catch (IOException e){
                System.out.println("Unable to create a screenshot file: " + e.getMessage());
            }
        }
    }
}
