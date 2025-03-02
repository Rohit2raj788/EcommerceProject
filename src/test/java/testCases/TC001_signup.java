package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.RegiterationPage;
import testBase.BaseClass;
import utilities.RandomDataGenerator;

import java.time.Duration;

public class TC001_signup extends BaseClass {


    @Test
    public void signUp(){
        try {

            logger.info("****Testcase Execution Started****");
            HomePage hp = new HomePage(driver);
            logger.info("---Clicked My Account---");
            hp.clickMyAccount();
            logger.info("---Clicked My Register link---");
            hp.clickRegister();
            RegiterationPage sp = new RegiterationPage(driver);
            logger.info("---Entering Registration Data---");
            sp.setTxt_Firstname(RandomDataGenerator.getRandomString(5));
            sp.setTxt_Lastname(RandomDataGenerator.getRandomString(5));
            sp.setTxt_email(RandomDataGenerator.getRandomAlphaNumeric(5) + "@gmail.com");
            sp.setTxt_telephone(RandomDataGenerator.getRandomNumeric(10));

            String password = RandomDataGenerator.getRandomAlphaNumeric(8) + "@";
            sp.setTxt_password(password);
            sp.setTxt_confirmPassword(password);
            sp.selectChk_privacyPolicy();
            sp.selectBtn_continue();
            logger.info("---Validating confirmation Message---");

            if(sp.getConfirmationMsg().equals("Your Account Has Been Created!") ){
                Assert.assertTrue(true);
            }
            else{
                logger.error("Test Failed");
                logger.debug("Debug Test");
                Assert.fail();
            }
        }
        catch (Exception e){
            logger.error("Test Failed due to Exception");
            Assert.fail();
        }

    }




}
