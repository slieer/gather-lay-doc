package com.skyworthbox.gather;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test2 {
    /**
     * @desc main method
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String driverPath = "/root/chrome/chromedriver";
        String targetURL = "";
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeDriver driver = getDriver(); // 对网页内容作处理
        Document doc = getDocument(targetURL);
        // 如果对网页做操作，直接使用Driver // 执行JS：滚动效果
        driver.executeScript("scrollTo(0,10000)"); // 等待加载示例 // 1. 超时加载等待
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); // 2. 等待加载组件
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".red_box")));
    }

    /**
     * }
     * 
     * @desc get chrome driver
     */
    public static ChromeDriver getDriver() {
        HashMap<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings", 2);
        prefs.put("profile.default_content_setting_values", 2);
        prefs.put("profile.managed_default_content_settings.images", 2);
        //String userAgent = AppForum.getAgents();
        String userAgent = "";
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/usr/bin/google-chrome-stable");
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--user-agent=" + userAgent, "--no-sandbox", "--test-type");
        options.addArguments("--disable-infobars", "--headless", "--disable-gpu",
                "--enable-strict-powerful-feature-restrictions");
        options.addArguments("--disable-plugins", "--disable-images", "--start-maximized");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setJavascriptEnabled(true);
        return new ChromeDriver(capabilities);
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
                driver.close();
            } catch (Exception e) {
            }
        }
        return doc;
    }
}
