package testCases;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObject.HomePage;
import pageObject.MacbookSearchPage;
import testBase.BaseClass;

public class TC004_Cart extends BaseClass {
    @Test
    public void AddtoCart() throws InterruptedException {
        HomePage hp= new HomePage(driver);
        hp.Enter_Search(po.getProperty("SearchProduct"));
        act.keyDown(Keys.CONTROL).keyDown(Keys.ENTER).keyUp(Keys.CONTROL).keyUp(Keys.ENTER).perform();
        MacbookSearchPage mp= new MacbookSearchPage(driver);
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",mp.getMacbook());
        mp.ClickMacbookAddtoCart();
        javascriptExecutor.executeScript("window.scrollTo(0, 0);");

        mp.Click_Cart();
        System.out.println(mp.getCartProductName());
        SoftAssert sa= new SoftAssert();
        sa.assertEquals(mp.getCartProductName(),"MacBook");
        for(WebElement element: mp.getCartProductDetails()){
            System.out.println(element.getText());
        }
        sa.assertEquals(mp.getProductpriceinCartPage(),mp.getProductpriceinSearchPage());
        sa.assertAll();

    }
}
