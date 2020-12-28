package com.appium.testcase;

import com.appium.testcase.contact.ContactPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MainPage extends BasePage{

    public MainPage(AndroidDriver driver, DesiredCapabilities capabilities) {
        super(driver, capabilities);
    }

    public MainPage() throws MalformedURLException {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "小江山");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "5.1.1");
        capabilities.setCapability("appPackage", "com.tencent.wework");
        capabilities.setCapability("appActivity", ".launch.LaunchSplashActivity");
        // .ui.LauncherUI
        capabilities.setCapability("noReset", "true");
        //对输入法的控制
        capabilities.setCapability("unicodeKeyboadr","true");
        capabilities.setCapability("resetKeyboard", "true");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
//    public static void initData() throws MalformedURLException {
//        capabilities = new DesiredCapabilities();
//        capabilities.setCapability("deviceName", "小江山");
//        capabilities.setCapability("automationName", "Appium");
//        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("platformVersion", "5.1.1");
//        capabilities.setCapability("appPackage", "com.tencent.wework");
//        capabilities.setCapability("appActivity", ".launch.LaunchSplashActivity");
//        // .ui.LauncherUI
//        capabilities.setCapability("noReset", "true");
//        //对输入法的控制
//        capabilities.setCapability("unicodeKeyboadr","true");
//        capabilities.setCapability("resetKeyboard", "true");
//        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//    }

    public ContactPage contact(){
        driver.findElement(By.xpath("//*[@text='通讯录']")).click();
        return new ContactPage(driver);
    }
}
