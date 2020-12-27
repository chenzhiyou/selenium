package com.appium.testcase;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class wxmicroTest {
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
        capabilities.setCapability("appPackage", "com.tencent.mm");
        capabilities.setCapability("appActivity", "com.tencent.mm.ui.LauncherUI");
        // .ui.LauncherUI
        capabilities.setCapability("noReset", "true");
        //对输入法的控制
        capabilities.setCapability("unicodeKeyboadr","true");
        capabilities.setCapability("resetKeyboard", "true");
//        capabilities.setCapability("chromedriverExecutableDir", "");
//        capabilities.setCapability("chromedriverChromeMappingFile", "");
//        capabilities.setCapability("showChromedriverLog", "");
        // 小程序自动化
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("androidProcess", "com.tenchent.mm:appbrand0");
        capabilities.setCapability("chromeOptions", "chromeOptions");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @Test
    public void wxmicroAApplication() throws InterruptedException {
        Dimension size = driver.manage().window().getSize();
        // 向下滑动出小程序
        Thread.sleep(10000);
        new TouchAction<>(driver)
                .longPress(
                        LongPressOptions.longPressOptions()
                            .withDuration(Duration.ofSeconds(2))
                            .withPosition(PointOption.point(size.width/2, size.height/4)))
                .moveTo(PointOption.point(size.width/2, size.height/10*8))
                .release().perform();
        driver.findElement(By.xpath("//*[@text=搜索小程序]")).click();
        driver.findElement(By.xpath("//*[@text=搜索小程序]")).sendKeys("雪球"+ Keys.ENTER);
        driver.findElement(By.className("android.widget.button")).click();
        driver.getContextHandles().stream().forEach(context ->{
            System.out.println(context.toString());
        });
        String webxview = driver.getContextHandles().stream().filter(context->context.toString()
                .contains("WEBVIEW_")).findFirst().get().toString();
        System.out.println(webxview);
        driver.context(webxview);

    }
}
