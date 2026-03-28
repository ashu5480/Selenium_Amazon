package PageClass;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Base.BaseClass;

public class AuthenticationOperations extends BaseClass{
    
    public AuthenticationOperations(){
          super();
    }

    JavascriptExecutor js = (JavascriptExecutor) getDriver();

    @FindBy(xpath="//div[@id='nav-link-accountList']")
    WebElement signUserButtonHover;

    @FindBy(id="nav-al-container")
    WebElement popUpSignInContainer;

    @FindBy(xpath="//div[contains(@id,'Cust')]//a")
    WebElement startHereButton;

    @FindBy(id="ap_email_login")
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

    public static void UserSignin(){
        
    }
}
