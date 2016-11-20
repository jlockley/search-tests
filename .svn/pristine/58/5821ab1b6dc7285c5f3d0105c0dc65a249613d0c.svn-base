package com.ec.web.drivers;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Factory to provide a {@link WebDriverPageOps} instance based on the supplied
 * configuration.
 */
public class WebDriverPageOpsFactory {
    private static final String WEBDRIVER_IE_DRIVER = "webdriver.ie.driver";
    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";

    private static final String IE = "IE";
    private static final String CHROME = "CHROME";
    private static final String FIREFOX = "FIREFOX";
    private static final String UNSUPPORTED_BROWSER_TYPE_FORMAT = "Unsupported browser type [%s]";

    
    public WebDriverPageOpsFactory(String ieDriverPath, String chromeDriverPath) {
        System.setProperty(WEBDRIVER_IE_DRIVER, ieDriverPath);
        System.setProperty(WEBDRIVER_CHROME_DRIVER, chromeDriverPath);
    }

    /**
     * @param browserType
     *            Currently supports either FIREFOX, CHROME OR IE
     * @param webDriverImplicitTimeout
     *            Required implicit timeout in seconds
     * @return 
     */
    public WebDriverPageOps create(String browserType, long webDriverImplicitTimeout, String baseUrl) {
        WebDriver driver;

        switch (browserType) {
        case IE: {
            driver = new InternetExplorerDriver();
            break;
        }
        case CHROME: {
            driver = new ChromeDriver();
            break;
        }
        case FIREFOX: {
            driver = new FirefoxDriver();
            break;
        }
        default: {
            throw new RuntimeException(String.format(UNSUPPORTED_BROWSER_TYPE_FORMAT, browserType));
        }
        }
        
        driver.manage().window().setSize(new Dimension(1400, 700));
        return new WebDriverPageOps(driver, webDriverImplicitTimeout, TimeUnit.SECONDS, baseUrl);
    }

}
