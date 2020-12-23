package com.appium.testcase;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
    @Test
    public void assertTest(){
        Integer a = new Integer(1);
        Integer b = new Integer(2);

        assertEquals(a, b);
        assertNotNull(a);
        assertTrue(false);
        assertFalse(true);
        assertNull(b);
        assertSame(a, b);
        assertNotSame(a, b);
    }
    @Test
    public void hamrestTest(){
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        //使用hamrest进行断言
        assertThat("判断a和b是否相等", a, equalTo(b));
    }
}
