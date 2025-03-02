package testCases;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObject.HomePage;
import pageObject.MacbookSearchPage;
import testBase.BaseClass;

public class TC003_Search extends BaseClass {

    @Test
    public void productlist(){
        try {
            HomePage hp = new HomePage(driver);
            hp.Enter_Search(po.getProperty("SearchProduct"));
            act.keyDown(Keys.CONTROL).keyDown(Keys.ENTER).keyUp(Keys.CONTROL).keyUp(Keys.ENTER).perform();
            MacbookSearchPage mp= new MacbookSearchPage(driver);
            SoftAssert sa= new SoftAssert();
            sa.assertEquals(mp.getProductlist().size(),Integer.parseInt(po.getProperty("NoOfProducts")));
            for(WebElement prod: mp.getProductlist()){

                if(prod.getText().equals("MacBook")||prod.getText().equals("MacBook Air")||prod.getText().equals("MacBook Pro")){
                    sa.assertTrue(true);
                }
                else{
                    sa.fail();
                }
                System.out.println(prod.getText());
            }
            sa.assertAll();
        }
        catch (Exception E){
            logger.info("Test Failed due to Exception");
            Assert.fail();
        }



    }

}
