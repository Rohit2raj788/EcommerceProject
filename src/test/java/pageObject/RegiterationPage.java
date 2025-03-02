package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegiterationPage extends BasePage {
    public RegiterationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement txt_Firstname;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txt_Lastname;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txt_email;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement txt_telephone;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txt_password;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement txt_confirmPassword;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement chk_privacyPolicy;


    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btn_continue;

    @FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
    WebElement msg_confirmationMsg;


    public void setTxt_Firstname(String FirstName) {
        txt_Firstname.sendKeys(FirstName);
    }

    public void setTxt_Lastname(String LastName) {
        txt_Lastname.sendKeys(LastName);
    }

    public void setTxt_email(String email) {
        txt_email.sendKeys(email);
    }

    public void setTxt_telephone(String telephonenumber) {
        txt_telephone.sendKeys(telephonenumber);
    }

    public void setTxt_password(String pass) {
        txt_password.sendKeys(pass);
    }

    public void setTxt_confirmPassword(String confirmPassword) {
        txt_confirmPassword.sendKeys(confirmPassword);
    }

    public void selectChk_privacyPolicy() {
        chk_privacyPolicy.click();
    }

    public void selectBtn_continue() {
        btn_continue.click();
    }


    public String getConfirmationMsg() {
        try {
            return msg_confirmationMsg.getText();
        }
        catch (Exception e) {
            return e.getMessage();

        }

    }




}

