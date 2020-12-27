package com.appium.testcase;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.GsmCallActions;
import org.aspectj.util.FileUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DevicesTest {
    public static DesiredCapabilities capabilities;
    public static AndroidDriver driver;

    @BeforeAll
    public static void initData() throws MalformedURLException {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "小江山");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "5.1.1");
        capabilities.setCapability("appPackage", "com.tencent.mm");
        capabilities.setCapability("appActivity", "com.tencent.mm.ui.LauncherUI");
        // .ui.LauncherUI
        capabilities.setCapability("noReset", "true");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @Test
    public void callTest(){
        // 打电话测试
        driver.makeGsmCall("55555555", GsmCallActions.CALL);
        //切换为WiFi模式
        driver.toggleWifi();
        // 切换为流量模式
        driver.toggleData();
        //切换为飞行模式
        driver.toggleAirplaneMode();
        //切换为横屏模式
        driver.rotate(ScreenOrientation.LANDSCAPE);
        //切换为竖屏模式
        driver.rotate(ScreenOrientation.PORTRAIT);
        // 锁屏
        driver.lockDevice();
    }

    public void sendSMSTest() throws IOException {
        // 打电话测试
        driver.sendSMS("555555", "send msg");
        // 截屏操作
        File screenshotAS = driver.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty(""));
        FileUtil.copyFile(screenshotAS, file);
    }
}
