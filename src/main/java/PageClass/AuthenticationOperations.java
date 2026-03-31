package PageClass;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Base.BaseClass;
import utilities.utility;

public class AuthenticationOperations extends BaseClass{
    
    public AuthenticationOperations(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath="//div[contains(@id, 'nav-link-accountList')]")
    WebElement AccountAndList;

    // Amazon can render either ap_email or ap_email_login depending on flow.
    @FindBy(css="#ap_email, #ap_email_login")
    WebElement emailLoginField;

    @FindBy(xpath="//span[@id='continue']")
    WebElement continueButton;

    @FindBy(xpath="//label[@for='ap_password']")
    WebElement passwordLabel;

    @FindBy(id="ap_password")
    WebElement passwordField;

    @FindBy(id="signInSubmit")
    WebElement signInSubmitButton;

    @FindBy(xpath="//div[contains(@class,'alert-container')]//div")
    WebElement errorMessage;

    @FindBy(xpath="//label[@for='auth-mfa-otpcode']")
    WebElement otpCodeLabel;

    @FindBy(id="auth-signin-button")
    WebElement signInButton;

    @FindBy(xpath="//div[@class='nav-line-1-container']//span")
    WebElement signInUserButton;

    public void UserSignin(String username,String Password){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        Actions actions = new Actions(getDriver());

        // Hover first, then click Sign in CTA from the flyout.
        utility.waitForElementToBeClickable(AccountAndList);
        actions.moveToElement(AccountAndList).perform();
        //utility.waitForElementToBeClickable(signInButton);
        AccountAndList.click();

        // Fallback: direct JS click on account menu if flyout click is blocked.
        try {
            utility.waitForElementToBeVisible(emailLoginField);
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", AccountAndList);
        }

        utility.waitForElementToBeVisible(emailLoginField);
        emailLoginField.clear();
        emailLoginField.sendKeys(username);
        utility.waitForElementToBeClickable(continueButton);
        continueButton.click();
        utility.waitForElementToBeVisible(passwordField);
        passwordField.clear();
        passwordField.sendKeys(Password);
        utility.waitForElementToBeClickable(signInSubmitButton);
        signInSubmitButton.click();

        boolean hasError = false;
        try {
            hasError = errorMessage.isDisplayed();
        } catch (Exception ignored) {
            // If the error element is not present, proceed with success assertions.
        }

        if(hasError){
            String Actual_Message = errorMessage.getText();
            String Expected_Message = utilities.Messages.passwordIncorrectMessage;
            Assert.assertEquals(Actual_Message, Expected_Message);
            System.out.println("Your Password is Wrong Please enter correct one");
            logger.info("Your Password is Wrong Please enter correct one");
            return;
        }

        utility.waitForElementToBeVisible(signInUserButton);
        String SuccessMessge = signInUserButton.getText();
        String Actual_Message = utilities.Messages.userVerification+" Ashu";
        Assert.assertEquals(SuccessMessge, Actual_Message);
    }
}
