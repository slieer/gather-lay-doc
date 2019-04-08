package com.skyworthbox.gather;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Test1 {

    private static final String CHROME_EXE = "D:/devTools/chrome/chrome-win/chrome.exe";
    private static final String DRIVER_PATH = "D:/devTools/chrome/chromedriver_74.0.3729.6.exe";

    /**
     * @desc main method
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String targetURL = "https://baidu.com";
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        ChromeDriver driver = getDriver();
        // 对网页内容作处理
        Document doc = getDocument(targetURL); // 如果对网页做操作，直接使用Driver
        
        System.out.println(doc.toString());
    }

    /**
     * @desc get chrome driver
     */
    public static ChromeDriver getDriver() {
        // String userAgent = AppForum.getAgents();
        ChromeOptions options = new ChromeOptions();
        options.setBinary(CHROME_EXE);
        options.addArguments("--no-sandbox", "--test-type", "--headless", "--disable-gpu");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setJavascriptEnabled(true);
        ChromeDriver driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        return driver;
    }

    /**
     * @desc crawler html content
     * @param url
     */
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

}
