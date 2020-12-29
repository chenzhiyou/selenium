package com.appium.testcase;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static DriverFactory instance;
    public static DriverFactory getInstance(){
        if(instance==null){
            instance = new DriverFactory();
        }else {
            return instance;
        }
        return instance;
    }
    public static WebDriver createDriver(String caps) throws MalformedURLException {
        String platformName = caps;
        WebDriver driver = null;
        if(platformName.equals("Android")){
            DesiredCapabilities capabilities = new DesiredCapabilities();
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

        }else if (platformName.equals("iOS")){

        }else if (platformName.equals("Web")){

        }
        return driver;
    }
}
