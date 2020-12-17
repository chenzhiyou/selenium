package com.seleniumStudy.testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class moveTest {
    public static WebDriver driver;
//    public static Actions actions;
    @BeforeAll
    public static void initData(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void moveTest(){
        driver.get("https://baidu.com");
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.id("s-usersetting-top")));
        action.perform();
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }
}
