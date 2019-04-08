package com.skyworthbox.gather;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium2Example {
    public static void main(String[] args) {
        testBaidu();
    }
    
    public static void testGoogle() {
        WebDriver driver = new FirefoxDriver();

        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Cheese!");
        element.submit();

        System.out.println("Page title is: " + driver.getTitle());

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        System.out.println("Page title is: " + driver.getTitle());

        // Close the browser
        driver.quit();
    }
    
    public static void testBaidu() {
        WebDriver driver = ChromeUtil.getDriver();
        driver.get("https://www.baidu.com");
        
        WebElement element = driver.findElement(By.id("kw"));
        element.sendKeys("Cheese!");
        
        driver.findElement(By.id("su")).submit();
        System.out.println("Page title is: " + driver.getTitle());

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                System.out.println(d.getCurrentUrl());
                System.out.println(d.toString());
                
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        System.out.println("Page title is: " + driver.getTitle());
        // Close the browser
        driver.quit();
        
    }
    
    
}
