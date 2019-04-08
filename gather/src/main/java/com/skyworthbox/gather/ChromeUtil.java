package com.skyworthbox.gather;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeUtil {
    private static final String CHROME_EXE = "D:/devTools/chrome/chrome-win/chrome.exe";
    private static final String DRIVER_PATH = "D:/devTools/chrome/chromedriver_74.0.3729.6.exe";

    static {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
    }

    public static void main(String[] args) {

        String targetURL = "https://baidu.com";
        // 对网页内容作处理
        Document doc = getDocument(targetURL); // 如果对网页做操作，直接使用Driver

        System.out.println(doc.toString());
    }

    /**
     * @desc get chrome driver
     */
    public static ChromeDriver getDriver() {
        // String userAgent = AppForum.getAgents();
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, buildOption());
        capabilities.setJavascriptEnabled(true);
        ChromeDriver driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.setLogLevel(Level.ALL);
        
        return driver;
    }

    public static Document getDocument(String url) {
        ChromeDriver driver = getDriver();
        Document doc = null;
        try {
            driver.get(url);
            doc = Jsoup.parse(driver.getPageSource());

        } catch (Exception e) {
        } finally {
            try {
                driver.quit();
            } catch (Exception e) {
            }
        }
        return doc;
    }

    static ChromeOptions buildOption() {
        String downloadFilepath = "f:\\Users";
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);

        ChromeOptions options = new ChromeOptions();
        options.setBinary(CHROME_EXE);
        // to disable browser extension popup
        options.addArguments("--no-sandbox", "--test-type", "--headless", "--disable-gpu", "--disable-extensions");
        options.setExperimentalOption("prefs", chromePrefs);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        
        return options;
    }

}
