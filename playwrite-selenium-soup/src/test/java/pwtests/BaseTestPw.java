package pwtests;

import com.microsoft.playwright.Browser;
import org.testng.annotations.BeforeClass;
import pw.BasePagePw;


public class BaseTestPw {
    protected static Browser browser;


    @BeforeClass
    public void setUp(){
        BasePagePw.startChromium(false,false);
    }

}
