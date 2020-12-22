package com.appium.testcase;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class ToastTest {
    public static DesiredCapabilities capabilities;
    public static AndroidDriver driver;
    public static void initData() throws MalformedURLException {
        //初始化环境变量
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "5.1.1");
        capabilities.setCapability("appPackage", "com.android.settings");
        capabilities.setCapability("appActivity", ".Settings");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    public void toastTest(){
        //获取toast控件的内容并打印
        System.out.println(driver.findElement(By.xpath("//*[@classs='android.widget.Toast']")).getText());
    }
}
