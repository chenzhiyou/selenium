package com.seleniumStudy.testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class JsTest {
    public static WebDriver driver;
    @BeforeAll
    public static void initData(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void JsTest() throws InterruptedException {
        driver.get("https://www.12306.cn/index/");
        JavascriptExecutor jsDriver = (JavascriptExecutor)driver;
        Thread.sleep(4000);
        jsDriver.executeScript("document.getElementById('train_date').value='2020-12-30'");
        Object data = jsDriver.executeScript("document.getElementById('train_date').value");
        Thread.sleep(4000);
        System.out.println(data);

    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }
}
