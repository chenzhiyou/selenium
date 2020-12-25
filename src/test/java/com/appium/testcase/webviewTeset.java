package com.appium.testcase;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class webviewTeset {
    public static DesiredCapabilities capabilities;
    public static AndroidDriver driver;

    @BeforeAll
    public static void initData() throws MalformedURLException {
        //初始化环境变量
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "小江山");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "5.1.1");
        capabilities.setCapability("appPackage", "com.xueqiu.android");
        capabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
        capabilities.setCapability("noReset", "true");
        capabilities.setCapability("chromedriverExecutableDir", "");
        capabilities.setCapability("chromedriverChromeMappingFile", "");
        capabilities.setCapability("showChromedriverLog", "");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void webView_nativeTest() {
        driver.findElement(By.xpath("//*[@text='交易']")).click();
        driver.findElement(By.xpath("//*[@text='A股开户']")).click();
    }

    @Test
    public void webViewTest() {
        driver.findElement(By.xpath("//*[@text='交易']")).click();
        for (int i = 0; i < 5; i++) {
            //打印上下文信息
            driver.getContextHandles().forEach(context -> System.out.println(context.toString()));
        }
        //切换到webview，在进行webview切换的时候，需要注意Chromedriver版本的对应，否则无法切换
        driver.context(driver.getContextHandles().toArray()[1].toString());
        //打印webview的信息
        driver.getWindowHandles().forEach(window -> {
            System.out.println(window);
            driver.switchTo().window(window);
            System.out.println(driver.getPageSource());
        });
    }
    @Test
    public void wxmicroApplication(){

    }
}