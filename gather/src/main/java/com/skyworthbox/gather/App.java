package com.skyworthbox.gather;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // System.out.println("Hello World!");
        String url = "http://wenshu.court.gov.cn/list/list/?sorttype=1&number=0.5191832203422626&guid=8a744b71-75c1-e1158bfd-5c764e544ebd&conditions=searchWord+2+AJLX++%E6%A1%88%E4%BB%B6%E7%B1%BB%E5%9E%8B:%E6%B0%91%E4%BA%8B%E6%A1%88%E4%BB%B6&conditions=searchWord+%E6%B0%91%E4%BA%8B%E6%A1%88%E7%94%B1+++%E4%B8%80%E7%BA%A7%E6%A1%88%E7%94%B1:%E6%B0%91%E4%BA%8B%E6%A1%88%E7%94%B1";

        getPage(url);
    }

    public static void getPage(String url) {
        WebDriver driver = ChromeUtil.getDriver();
        driver.get(url);

        //Actions action = new Actions(driver);

        List<WebElement> elementList = driver.findElements(By.cssSelector(".scxz div:nth-child(2) img"));
        for (WebElement el : elementList) {
            //System.out.println("------------------------" + el.getTagName());
            System.out.println("------------------------" + el.getAttribute("sytle"));
            System.out.println("------------------------" + el.getAttribute("onclick"));
            System.out.println("------------------------" + el.getAttribute("src"));
            //System.out.println("------------------------" + el.getAttribute("alt"));
            
            el.click();
            //action.click(el);
        }

        System.out.println("Page title is: " + driver.getTitle());

        (new WebDriverWait(driver, 15)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                //System.out.println(d.getCurrentUrl());
                //d.getTitle().toLowerCase().startsWith("cheese!");
                return true;
            }
        });

        System.out.println("Page title is: " + driver.getTitle());
        // Close the browser
        driver.quit();

    }
}
