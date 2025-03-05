package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MacbookSearchPage extends BasePage{
    public MacbookSearchPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//*[@class=\"row\"]//*[contains(@class,'product-layout product-grid ')]//h4//a")
    private List<WebElement> Productlist;
    @FindBy(xpath = "//a[text()='MacBook']")
    private WebElement Macbook;
    @FindBy(xpath = "//*[@class=\"product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12\"][1]//span[text()='Add to Cart']")
    private WebElement lnk_MaacbookAddtoCart;

    @FindBy(xpath = "//*[@id='cart-total']")
    private WebElement lnk_Cart;

    @FindBy(xpath = "//tr//td[2]//a")
    private WebElement cartProductTitle;

    @FindBy(xpath = "//ul[@class='dropdown-menu pull-right']//li[2]//td")
    private List<WebElement> cartProductDetails;

    @FindBy(xpath = "(//*[@class='price'])[1]")
    private WebElement productPriceinSearchPage;

    @FindBy(xpath = "//*[@class='table table-bordered']//tr[4]//td[2]")
    private WebElement ProductPriceinCartPage;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    private WebElement addtoCartSuccessMsg;




    public List<WebElement> getProductlist(){
        return Productlist;
    }
    public WebElement getMacbook(){return Macbook;}
    public void ClickMacbookAddtoCart(){
        lnk_MaacbookAddtoCart.click();
    }
    public void Click_Cart(){
        getWait(10).until(ExpectedConditions.visibilityOf(addtoCartSuccessMsg));
        getWait(10).until(ExpectedConditions.visibilityOf(lnk_Cart));
        for (int i = 0; i < 3; i++) { // Try clicking up to 3 times if stale
            try {
                getWait(10).until(ExpectedConditions.elementToBeClickable(lnk_Cart)).click();
                break; // If successful, break out of loop
            } catch (StaleElementReferenceException e) {
                System.out.println("⚠️ Stale Element Exception! Retrying...");
            }
        }

    }
    public String getCartProductName(){
         return cartProductTitle.getText();
    }
    public List<WebElement> getCartProductDetails(){
        return cartProductDetails;
    }
    public String getProductpriceinSearchPage(){
        return productPriceinSearchPage.getText();
    }
    public String getProductpriceinCartPage(){
        return productPriceinSearchPage.getText();
    }



}
