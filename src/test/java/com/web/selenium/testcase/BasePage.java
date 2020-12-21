package com.web.selenium.testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
    //driver是一个实例对象
    WebDriver driver;
    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public BasePage() {
    }

    public void click(By by){
        driver.findElement(by).click();
    }

    public void sendKeys(By by, String content){
        driver.findElement(by).sendKeys(content);
    }
}
