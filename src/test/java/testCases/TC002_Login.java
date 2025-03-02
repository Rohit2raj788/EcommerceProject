package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.AccountPage;
import pageObject.HomePage;
import pageObject.LoginPage;
import testBase.BaseClass;

public class TC002_Login extends BaseClass {
    @Test
    public void validateLogin(){
        try {
            logger.info("-- Navigating Home Page---");
            HomePage hp= new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();
            logger.info("-- entering data  in Login Page---");
            LoginPage lp= new LoginPage(driver);
            lp.setTxt_Email(po.getProperty("email"));
            lp.setTxt_Password(po.getProperty("pass"));
            lp.clickbtn_login();

            AccountPage ap=new AccountPage(driver);

            if (driver.getTitle().equals("My Account")){
                Assert.assertTrue(true);
            }
            else {
                logger.info("Test Failed");
                Assert.fail();
            }

        }
        catch (Exception E){
            logger.info("Test Failed due to Exception");
            Assert.fail();
        }


    }
}
