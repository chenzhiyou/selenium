package com.web.selenium.testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
    WebDriver driver;
    public void click(By by){
        driver.findElement(by).click();
    }

    public void snedKeys(By by, String content){
        driver.findElement(by).sendKeys(content);
    }
}
