import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.UUID;

public class RegistrationPage {
    public static final String LANDINGPAGE_URL = "http://training.skillo-bg.com:4200/posts/all";
    public static final String REGISTRATION_URL = "http://training.skillo-bg.com:4200/users/register";
    private final WebDriver driver;

    @FindBy(id = "nav-link-login")
    public static WebElement loginLink;

    @FindBy(xpath = "//app-login//a[contains(text(),'Register')]")
    public static WebElement registerLink;

    @FindBy(xpath = "//input[@formcontrolname='username']")
    public static WebElement usernameField;

    @FindBy(xpath = "//input[@formcontrolname='email']")
    public static WebElement emailField;

    @FindBy(id = "defaultRegisterFormPassword")
    public static WebElement passwordField;

    @FindBy(id = "defaultRegisterPhonePassword")
    public static WebElement confirmPasswordField;

    @FindBy(id = "sign-in-button")
    public static WebElement signInButton;

    public RegistrationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isLandingPageLoaded() {
        WebDriverWait landingWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return landingWait.until(ExpectedConditions.urlToBe(LANDINGPAGE_URL));
    }

    public boolean isRegistrationUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlToBe(REGISTRATION_URL));
    }

    public void navigateToLandingPage() {
        driver.get(LANDINGPAGE_URL);
    }

    public void navigateToRegistration() {
        driver.get(REGISTRATION_URL);
    }

    public void clickLoginLink() {
        loginLink.click();
    }

    public void clickRegisterLink() {
        registerLink.click();
    }

    public String getUsernameFieldValue() {
        return usernameField.getAttribute("value");
    }

    public String getEmailFieldValue() {
        return emailField.getAttribute("value");
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void confirmPassword(String password) {
        confirmPasswordField.sendKeys(password);
    }

    public boolean arePasswordsMatching() {
        String password = passwordField.getAttribute("value");
        String confirmPassword = confirmPasswordField.getAttribute("value");
        return password.equals(confirmPassword);
    }

    public boolean isSignInButtonEnabled() {
        return signInButton.isEnabled();
    }

    public void clickSignInButton() {
        signInButton.click();
    }

    public boolean isRedirectedAfterRegistration() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4200/posts/all"));
    }

    public static String generateRandomUsername() {
        String username = UUID.randomUUID().toString().replaceAll("-", "");
        return username.substring(0, 6);
    }

    public static String generateRandomEmail() {
        String newEmailAddress = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
        String domain = "te.st";
        return newEmailAddress + "@" + domain;
    }

    public static String generateRandomPassword() {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = upperCaseLetters.toLowerCase();
        String numbers = "0123456789";
        String allCharacters = upperCaseLetters + lowerCaseLetters + numbers;
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int randomIndex = (int) (Math.random() * allCharacters.length());
            password.append(allCharacters.charAt(randomIndex));
        }
        return password.toString();
    }

}
