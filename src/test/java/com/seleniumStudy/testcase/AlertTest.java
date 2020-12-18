package com.seleniumStudy.testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class AlertTest {
    public static WebDriver driver;

    @BeforeAll
    public static void initData(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @Test
    public void alertTest() throws InterruptedException {
        driver.get("https://www.runoob.com/try/try.php?filename=jqueryui-api-droppable");
        driver.switchTo().frame("iframeResult");
        Actions actions = new Actions(driver);
        actions.dragAndDrop(driver.findElement(By.id("draggable")),driver.findElement(By.id("droppable"))).perform();
        Thread.sleep(4000);
        driver.switchTo().alert().accept();
        Thread.sleep(4000);
        driver.switchTo().parentFrame();
        String text = driver.findElement(By.xpath("//button[@class='btn btn-success']")).getText();
        System.out.println(text);
    }
    @AfterAll
    public static void tearDown(){
        driver.quit();
    }
}
