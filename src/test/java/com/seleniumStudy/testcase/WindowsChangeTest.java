package com.seleniumStudy.testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WindowsChangeTest {
    public static WebDriver driver;

    @BeforeAll
    public static void initData(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void windowChangeTest(){
        driver.get("https://baidu.com");
        driver.findElements(By.xpath("//a[@name='tj_login']")).get(1).click();
//        获取当前的handle
        String winhadle = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[@class='pass-reglink pass-link']")).click();
        for (String win:driver.getWindowHandles()){
            if(!win.equals(winhadle)){
//                窗口切换
                driver.switchTo().window(win);
            }
        }

    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }
}
