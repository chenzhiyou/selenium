package com.appium.testcase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BasePage {
    protected AndroidDriver driver;

    public  BasePage(AndroidDriver driver, DesiredCapabilities capabilities){
        this.driver = driver;
    }

    public BasePage() {

    }

    public BasePage(AppiumDriver driver) {

    }
}
