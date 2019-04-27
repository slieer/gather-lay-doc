package com.skyworthbox.chrome.auto;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class DocGather {
    private static final String CHROME_EXE = "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe";
    private static final String DOWNLOAD_FILE_PATH = "d:/Users/localserv/Downloads/webdriver/download";
    private static final String DRIVER_PATH = "./src/main/driver/chromedriver_win32/chromedriver_74.0.3729.6.exe";

    static {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
    }	
	
	private Selenium selenium;
	private WebDriver driver; 
	
	@Before
	public void setUp() throws Exception {
		driver = getDriver();						
		String baseUrl = "https://www.katalon.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testGather() throws Exception {
		selenium.windowMaximize();
		selenium.open("http://wenshu.court.gov.cn/list/list/?sorttype=1&number=0.5191832203422626&guid=8a744b71-75c1-e1158bfd-5c764e544ebd&conditions=searchWord+2+AJLX++%E6%A1%88%E4%BB%B6%E7%B1%BB%E5%9E%8B:%E6%B0%91%E4%BA%8B%E6%A1%88%E4%BB%B6&conditions=searchWord+%E6%B0%91%E4%BA%8B%E6%A1%88%E7%94%B1+++%E4%B8%80%E7%BA%A7%E6%A1%88%E7%94%B1:%E6%B0%91%E4%BA%8B%E6%A1%88%E7%94%B1");
//		new WebDriverWait(driver, 10).until(ExpectedConditions
//				.presenceOfElementLocated(By.id("resultList")));
		selenium.waitForPageToLoad("10000");
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  //转换时间格式
		String time = dateFormat.format(Calendar.getInstance().getTime());  //获取当前时间
		
		TakesScreenshot screenshot = (TakesScreenshot)driver;
        File src= screenshot.getScreenshotAs(org.openqa.selenium.OutputType.FILE);
        FileUtils.copyFile(src, new File(DOWNLOAD_FILE_PATH + "/" + time + ".png"));
		
		selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='【裁判理由】'])[1]/following::img[2]");
		selenium.selectWindow("win_ser_1");
		selenium.close();
		selenium.selectWindow("win_ser_local");
		selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='【裁判理由】'])[2]/following::img[2]");
		selenium.selectWindow("win_ser_2");
		selenium.close();
		selenium.selectWindow("win_ser_local");
		selenium.click("link=2");
		selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='（2014）格执字第101-3号'])[1]/following::img[2]");
		selenium.selectWindow("win_ser_3");
		selenium.close();
		selenium.selectWindow("win_ser_local");
		selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='【裁判理由】'])[2]/following::img[2]");
		selenium.selectWindow("win_ser_4");
		selenium.close();
		selenium.selectWindow("win_ser_local");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
	
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
	
    static ChromeOptions buildOption() {
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", DOWNLOAD_FILE_PATH);

        ChromeOptions options = new ChromeOptions();
        options.setBinary(CHROME_EXE);
        // to disable browser extension popup
        options.addArguments("--no-sandbox", "--test-type", "--headless", "--disable-gpu", "--disable-extensions");
        options.setExperimentalOption("prefs", chromePrefs);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        
        return options;
    }	
	
}
