package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {


    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@title='My Account']")
    private WebElement lnk_MyAccount;
    @FindBy(xpath = "//a[text()='Register']")
    private WebElement lnk_Register;
    @FindBy(xpath = "//*[text()='Login']")
    private WebElement lnk_Login;
    @FindBy(xpath = "//*[@name='search']")
    private WebElement txt_Search;


    public void clickMyAccount(){
        lnk_MyAccount.click();
    }

    public void clickRegister(){
        lnk_Register.click();
    }

    public void clickLogin(){lnk_Login.click();}
    public void Enter_Search(String searchitem){
        txt_Search.sendKeys(searchitem);
    }


}

