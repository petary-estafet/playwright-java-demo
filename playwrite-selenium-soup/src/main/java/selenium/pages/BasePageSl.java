package selenium.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v101.network.Network;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ResourcesBundles;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class BasePageSl {

    protected WebDriver driver;
    protected DevTools devTools;


    public BasePageSl(WebDriver localDriver){
        this.driver = localDriver;
    }


    public void click(By by) {
        driver.findElement(by).click();
    }

    public void goTo(String  url) {
        driver.navigate().to(ResourcesBundles.getEnvProperty("ENV_URI") + url );
    }

    public void type(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    public void useKeyboardKeys(By by, Keys keys) {
        driver.findElement(by).sendKeys(keys);
    }

    public void refreshPage(){
        driver.navigate().refresh();
    }

    public List<WebElement> getAllElements(By by){
        return driver.findElements(by);
    }

    public WebElement getWebElClickable(By by, int waitSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.ignoring(StaleElementReferenceException.class).until(
                ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(by)));
    }

    public void typeKeys(By by, Keys key) {
        driver.findElement(by).sendKeys(key);
    }



    public void executeScript(String script) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script);
    }

    public void jsClick(By by){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(by);
        js.executeScript("arguments[0].checked = true; arguments[0].click();", element);
    }


    public String getElementText(By by) {
        return driver.findElement(by).getText();
    }

    public String getElementAttribute(By by, String attribute) {
        return driver.findElement(by).getAttribute(attribute);
    }

    public void actionsClick(By by){
        var el = driver.findElement(by);
        Actions builder = new Actions(driver);
        builder.moveToElement(el).click(el);
        builder.perform();
    }


    public void waitForElement(By by, int duration) {
        new WebDriverWait(driver, Duration.ofSeconds(duration))
                .until(driver -> driver.findElement(by).isDisplayed());
    }


    public String getCookie(String cookieName){
        Cookie cookie = driver.manage().getCookieNamed(cookieName);
        System.out.println(cookie);
        return cookieName;
    }

    public Set<Cookie> getAllCookies(){
        Set<Cookie> cookies = driver.manage().getCookies();
        for(Cookie cookie: cookies){
            System.out.println(cookie.getName());
            System.out.println(cookie.getValue());
        }
        return driver.manage().getCookies();
    }

    public void verifyNetworkResponseBody(String uri, String expectedBody) {
        devTools.createSession();

        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty()));

        List<Boolean> isFail = new ArrayList<>();

        devTools.addListener(Network.responseReceived(),
                res -> {
                    if (res.getResponse().getUrl().contains(uri)) {
                        var requestId = res.getRequestId();
                        System.out.println(devTools.send(Network.getResponseBody(requestId)).getBody());
                        String body = devTools.send(Network.getResponseBody(requestId)).getBody();
                        if (!body.contains(expectedBody)) {
                            System.out.println("Response body doesn't contain: " + expectedBody);
                            driver.close();
                        }
                    }
                }
        );
        devTools.close();
    }

    public void getNetworkResponse(String uri){
        devTools.createSession();

        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty()));
        devTools.addListener(Network.responseReceived(),
                res -> {
                    if (res.getResponse().getUrl().contains(uri)) {
                        var requestId = res.getRequestId();
                        System.out.println(devTools.send(Network.getResponseBody(requestId)).getBody());
                        String body = devTools.send(Network.getResponseBody(requestId)).getBody();
                    }
                });
        devTools.close();
    }
}
