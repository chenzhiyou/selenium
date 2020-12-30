package com.frameWork;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class MainPage extends BasePage{
    public MainPage(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://ceshiren.com");
    }

    public SearchPage search(){
        //实现PO，可以使用Java代码，也可以main_po.yaml
        return new SearchPage(driver);
    }
    // 注册方法，不建议
    public void stepRunDemo(String method){
        if(method.equals("search")){
            this.search();
        }
    }
}
