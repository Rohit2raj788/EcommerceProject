package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MacbookSearchPage extends BasePage{
    public MacbookSearchPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//*[@class=\"row\"]//*[contains(@class,'product-layout product-grid ')]//h4//a")
    private List<WebElement> Productlist;

    public List<WebElement> getProductlist(){
        return Productlist;
    }


}
