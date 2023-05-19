package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;


public class SharedDriver {
    public  WebDriver driver;
    public static DevTools devTools;

    public  WebDriver startChromeBrowser(){


        if(ResourcesBundles.getEnvProperty("browser").equals("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setBinary(ResourcesBundles.getEnvProperty("binary_path"));
            options.addArguments("--remote-allow-origins=*");

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
            driverBaseSettings();
        }
        if(ResourcesBundles.getEnvProperty("browser").equals("FF")) {
            FirefoxOptions options = new FirefoxOptions();
//            options.setBinary("D:/Users/yordanovp/AppData/Local/Mozilla Firefox/firefox.exe");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(options);
            driverBaseSettings();
        }


        return driver;
    }

    public static DevTools setDevTools(WebDriver driver){
        devTools = ((ChromeDriver) driver).getDevTools();
        return devTools;
    }

    public static DevTools setFfDevTools(WebDriver driver){
        devTools = ((FirefoxDriver) driver).getDevTools();
        return devTools;
    }

    private void driverBaseSettings(){
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        driver.get(ResourcesBundles.getEnvProperty("ENV_URI"));
    }

}
