package com.seleniumStudy.testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class UploadTest {
    public static WebDriver driver;
    @BeforeAll
    public static void initData(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void uploadTest() throws InterruptedException {
        driver.get("https://baidu.com");
        driver.findElement(By.xpath("//span[@class='soutu-btn']")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//input[@class='upload-pic']")).sendKeys("/Users/lixiaochen/Downloads/1.jpeg");
        Thread.sleep(4000);

    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }
}
