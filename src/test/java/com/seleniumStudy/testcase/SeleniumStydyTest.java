package com.seleniumStudy.testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class SeleniumStydyTest {
    public static WebDriver driver;
    @BeforeAll
    public static void initData(){
        driver = new ChromeDriver();
//        添加隐式等待，设置以秒为单位
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        添加显示等待
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver){
                return driver.findElement(By.id("kw"));
            }
        });

    }

    @Test
    public void openDriver(){
        driver.get("https://www.baidu.com");
        driver.findElement(By.id("kw")).sendKeys("selenium");
        driver.findElement(By.id("su")).click();

    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }
}
