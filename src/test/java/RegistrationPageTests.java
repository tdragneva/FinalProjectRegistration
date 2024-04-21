import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.IOException;

public class RegistrationPageTests {
    private BrowserManager browserManager;

    @BeforeClass
    public void setUpTestClass() throws IOException {
        browserManager = new BrowserManager();
        browserManager.setupTestClass();
    }

    @BeforeMethod
    public void setUpTest()  {
        BrowserManager.initializeDriver();
    }

    @AfterMethod
    public void tearDownTest(ITestResult testResult) {
        browserManager.takeScreenshot(testResult);
        BrowserManager.closeCurrentWindow();
            }

    @AfterClass
    public void deleteFiles() throws IOException {
        browserManager.deleteDownloadFiles();
        browserManager.cleanDirectory(BrowserManager.DOWNLOAD_DIR);
        BrowserManager.quitDriver();
    }

    @Test(priority = 1)
    public void registerLinkNavigation() {
        BrowserManager.registerPage.navigateToLandingPage();
        Assert.assertTrue(BrowserManager.registerPage.isLandingPageLoaded(), "Landing page not loaded.");
        BrowserManager.registerPage.clickLoginLink();
        BrowserManager.registerPage.clickRegisterLink();
        Assert.assertTrue(BrowserManager.registerPage.isRegistrationUrlLoaded(), "Registration page not loaded");
    }

    @Test(priority = 2)
    public void registrationElementFieldsEnabled() {
        BrowserManager.registerPage.navigateToRegistration();
        Assert.assertTrue(BrowserManager.registerPage.isRegistrationUrlLoaded(), "Registration page not loaded");

        Assert.assertTrue(RegistrationPage.usernameField.isEnabled(), "Username field not enabled.");
        Assert.assertTrue(RegistrationPage.emailField.isEnabled(), "Email field not enabled");
        Assert.assertTrue(RegistrationPage.passwordField.isEnabled(), "Password field not enabled.");
        Assert.assertTrue(RegistrationPage.confirmPasswordField.isEnabled(), "Confirm password field not enabled.");
    }

    @Test(priority = 3)
    public void usernameEmailFieldsDataSubmission() {
        BrowserManager.registerPage.navigateToRegistration();
        Assert.assertTrue(BrowserManager.registerPage.isRegistrationUrlLoaded(), "Registration page not loaded");

        String username = RegistrationPage.generateRandomUsername();
        String email = RegistrationPage.generateRandomEmail();

        BrowserManager.registerPage.enterUsername(username);
        BrowserManager.registerPage.enterEmail(email);

        Assert.assertEquals(BrowserManager.registerPage.getUsernameFieldValue(), username, "The entered username does not match the filled in username.");
        Assert.assertEquals(BrowserManager.registerPage.getEmailFieldValue(), email, "The entered email does not match the filled in email.");
    }

    @Test(priority = 4)
    public void passwordsMatchValidation() {
        BrowserManager.registerPage.navigateToRegistration();
        Assert.assertTrue(BrowserManager.registerPage.isRegistrationUrlLoaded(), "Registration page not loaded");

        String password = RegistrationPage.generateRandomPassword();

        BrowserManager.registerPage.enterPassword(password);
        BrowserManager.registerPage.confirmPassword(password);

        Assert.assertTrue(BrowserManager.registerPage.arePasswordsMatching(), "The entered password in the first field does not match the password in the second field.");
    }

    @Test(priority = 5)
    public void isSignInButtonEnabled() {
        BrowserManager.registerPage.navigateToRegistration();
        Assert.assertTrue(BrowserManager.registerPage.isRegistrationUrlLoaded(), "Registration page not loaded");

        Assert.assertTrue(BrowserManager.registerPage.isSignInButtonEnabled(), "The Sign in button is not enabled.");
    }

    @Test(priority = 6)
    public void redirectionAfterRegistration() {
        BrowserManager.registerPage.navigateToRegistration();
        Assert.assertTrue(BrowserManager.registerPage.isRegistrationUrlLoaded(), "Registration page not loaded");

        String username = RegistrationPage.generateRandomUsername();
        String email = RegistrationPage.generateRandomEmail();
        String password = RegistrationPage.generateRandomPassword();

        BrowserManager.registerPage.enterUsername(username);
        BrowserManager.registerPage.enterEmail(email);
        BrowserManager.registerPage.enterPassword(password);
        BrowserManager.registerPage.confirmPassword(password);

        BrowserManager.registerPage.clickSignInButton();

        Assert.assertTrue(BrowserManager.registerPage.isRedirectedAfterRegistration(), "Not redirected after registration");
    }
}
