package pw;

import com.microsoft.playwright.*;
import utils.ResourcesBundles;

public class BasePagePw {

    public static Browser browser;
    public  Page page;
    Locator.ClickOptions clickOptions = new Locator.ClickOptions();

    public BasePagePw(Page page) {
        buildBPage(ResourcesBundles.getEnvProperty("ENV_URI"));
    }

    public static void startChromium(boolean isHeadless, boolean isUsingDevtools){
        browser = Playwright
                .create()
                .chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(isHeadless).setDevtools(isUsingDevtools));
        browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 1200));
    }

    public  void buildBPage(String url){
        page = browser.newPage();
        page.navigate(url, new Page.NavigateOptions()
                .setTimeout(15000));
    }

    public  void gotoPage(String route){
        page.navigate(ResourcesBundles.getEnvProperty("ENV_URI") + route);
    }

    public  void click(String selector){
        page.waitForSelector(selector).click();
    }

    public  String getText(String selector){
        return page.waitForSelector(selector).innerText();
    }

    public  void hover(String selectorHover){
        page.hover(selectorHover);
    }

    public  void clickHidden(String selector){
        page.locator(selector).click(clickOptions.setForce(true));
    }



    public  void check(String selector){
        page.waitForSelector(selector).check();
    }

    public  void type(String selector, String text){
        page.waitForSelector(selector).type(text);
    }
}
