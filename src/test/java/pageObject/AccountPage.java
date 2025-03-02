package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage{
    public AccountPage(WebDriver driver){
        super(driver);
    }
    @FindBy(xpath = "//*[text()='Account']")
    private WebElement lnk_Account;

    public String get_title(){
        return lnk_Account.getText();
    }
}
