package com.seleniumStudy.testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class ActionSeleniumTest {
    public static WebDriver driver;
//    public static Actions actions;
    @BeforeAll
    public static void initData(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @Test
    public void actionTest() throws InterruptedException {
        driver.get("http://sahitest.com/demo/clicks.htm");
        Actions actions = new Actions(driver);
        Thread.sleep(3000);
        actions.doubleClick(driver.findElement(By.xpath("//input[@value='dbl click me']")));
        actions.click(driver.findElement(By.xpath("//input[@value='right click me']")));
        actions.contextClick(driver.findElement(By.xpath("//input[@value='right click me']")));
        actions.perform();


    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }
}
