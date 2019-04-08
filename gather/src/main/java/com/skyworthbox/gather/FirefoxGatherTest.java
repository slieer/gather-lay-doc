package com.skyworthbox.gather;

import java.io.File;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class FirefoxGatherTest {
    private Selenium selenium;

    @Before
    public void setUp() throws Exception {
        System.out.println(LocalDateTime.now().toString());
        String basePath = "./src/main/driver/";
        String downloadDir = "./src/main/download";

        File file = new File(basePath);
        System.out.println(file.getAbsolutePath() + "," + file.exists());

        String firefoxDir = basePath + "/Mozilla Firefox/firefox.exe";
        if(!new File(firefoxDir).exists()) {
            System.out.println("use system default firefox app.");
            firefoxDir = "D:/Program Files/Mozilla Firefox/firefox.exe";
        }
        
        System.setProperty("webdriver.firefox.bin", firefoxDir);
        System.setProperty("webdriver.gecko.driver", basePath + "geckodriver-v0.24.0-win64/geckodriver.exe");

        FirefoxOptions options = new FirefoxOptions();
        options.setLogLevel(FirefoxDriverLogLevel.DEBUG);
        options.setHeadless(true);
        WebDriver driver = new FirefoxDriver(options);

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", downloadDir);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "binary/octet-stream");

        String baseUrl = "https://www.katalon.com/";
        selenium = new WebDriverBackedSelenium(driver, baseUrl);
        System.out.println(LocalDateTime.now().toString());
    }

    @Test
    public void testUntitledTestCase() throws Exception {
        selenium.open(
                "http://wenshu.court.gov.cn/list/list/?sorttype=1&number=0.5191832203422626&guid=8a744b71-75c1-e1158bfd-5c764e544ebd&conditions=searchWord+2+AJLX++%E6%A1%88%E4%BB%B6%E7%B1%BB%E5%9E%8B:%E6%B0%91%E4%BA%8B%E6%A1%88%E4%BB%B6&conditions=searchWord+%E6%B0%91%E4%BA%8B%E6%A1%88%E7%94%B1+++%E4%B8%80%E7%BA%A7%E6%A1%88%E7%94%B1:%E6%B0%91%E4%BA%8B%E6%A1%88%E7%94%B1");

        System.out.println(LocalDateTime.now().toString());
        selenium.waitForPageToLoad("5000");
        System.out.println(LocalDateTime.now().toString());
        selenium.click("id=condtion");
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='【裁判理由】'])[1]/following::img[2]");
        selenium.selectWindow("win_ser_1");
        selenium.close();
        selenium.selectWindow("win_ser_local");
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='【裁判理由】'])[2]/following::img[2]");
        selenium.selectWindow("win_ser_2");
        selenium.close();
        selenium.selectWindow("win_ser_local");
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='【裁判理由】'])[4]/following::img[2]");
        selenium.selectWindow("win_ser_3");
        selenium.close();
        selenium.selectWindow("win_ser_local");
        selenium.click("link=2");
        
        System.out.println(LocalDateTime.now().toString());
    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }
}
