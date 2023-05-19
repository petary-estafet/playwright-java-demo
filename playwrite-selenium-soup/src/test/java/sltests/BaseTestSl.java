package sltests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import selenium.pages.ToDosPage;
import utils.SharedDriver;

public class BaseTestSl {

    WebDriver driver;
    ToDosPage toDosPage;


    @BeforeClass
    public void setUp(){
        driver = new SharedDriver().startChromeBrowser();
        toDosPage = new ToDosPage(driver);


    }

    @AfterClass
    public  void tearDown(){
        driver.close();
    }
}
