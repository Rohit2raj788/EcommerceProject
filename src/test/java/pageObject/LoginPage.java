package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver){
        super(driver);
    }
    @FindBy(xpath = "//*[@id='input-email']")
    private WebElement txt_Email;

    @FindBy(xpath = "//*[@id='input-password']")
    private WebElement txt_Password;

    @FindBy(xpath = "//*[@value='Login']")
    private WebElement btn_Login;

    public void setTxt_Email(String email) {
        txt_Email.sendKeys(email);
    }
    public void setTxt_Password(String Password) {
        txt_Password.sendKeys(Password);
    }
    public void clickbtn_login() {
        btn_Login.click();
    }


}
