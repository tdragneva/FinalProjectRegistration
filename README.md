Final Project for Auto12 Course

This project contains automated tests for the Skillo training website's registration. The tests are implemented using Selenium WebDriver and JUnit, and they cover different aspects of the registration process, including navigation, field validation, data submission, password matching, button validation, and redirection after registration.

Prerequisites

    Java 11 or higher installed
    Maven installed
    Chrome browser installed


Setup Instructions

    Clone this repository to your local machine.
    Ensure that Java and Maven are properly installed.
    Configure your IDE to use JUnit for test execution.
    Download and install the appropriate ChromeDriver for your Chrome browser version.
    Open the project in your preferred IDE.
    Build the project using Maven.


Test Cases
1. Register Link Navigation

    Description: Verifies that clicking on the registration link navigates to the registration page.
    Test Method: registerLinkNavigation()

2. Registration Element Fields Enabled

    Description: Ensures that all required fields on the registration page are enabled for user input.
    Test Method: registrationElementFieldsEnabled()

3. Username and Email Fields Data Submission

    Description: Validates that entered username and email match the values submitted during registration.
    Test Method: usernameEmailFieldsDataSubmission()

4. Passwords Match Validation

    Description: Checks if the entered password matches the confirmed password during registration.
    Test Method: passwordsMatchValidation()

5. Sign-In Button Enabled

    Description: Tests whether the Sign-In button is enabled after filling out the registration form.
    Test Method: isSignInButtonEnabled()

6. Redirection After Registration

    Description: Verifies that users are redirected to the correct page after successful registration.
    Test Method: redirectionAfterRegistration()


Author

Teodora Dragneva

License

This project is licensed under the MIT License - see the LICENSE file for details.




