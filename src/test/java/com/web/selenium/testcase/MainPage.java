package com.web.selenium.testcase;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MainPage extends BasePage{
    //初始化方法
    public void initData() throws InterruptedException, IOException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        File file = new File("cookies.yaml");
        if(file.exists()) {
            // 使用yaml文件中的cookies信息进行登录，登录作为前置条件，移动到beforeall中进行操作
            driver.get("https://work.weixin.qq.com/wework_admin/frame");
            Thread.sleep(10000);
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference<List<HashMap<String, Object>>> typeReference = new TypeReference<List<HashMap<String, Object>>>() {
            };
            List<HashMap<String, Object>> cookies = mapper.readValue(file, typeReference);
            System.out.println(cookies);
            cookies.forEach(cookieMap -> {
                driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(), cookieMap.get("value").toString()));
            });
//      刷新界面
            driver.navigate().refresh();
            Thread.sleep(4000);
//        }
        }else{
            neddLogin();
        }

    }

    //登录的方法
    public void neddLogin() throws IOException {
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        try {
            Thread.sleep(20000);
//          将读取大到的cookies信息存储起来
            Set<Cookie> cookies  = driver.manage().getCookies();
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.writeValue(new File("cookies.yaml"), cookies);
            System.exit(0);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //driver为实例方法
    public MainPage() throws IOException, InterruptedException {
        //初始化Selenium，复用session，打开网站
        this.initData();
    }

    public ContactPage contact(){
        //进入通讯录
        click(By.cssSelector("[href='#contacts']"));
        //传递Selenium的driver给另外一个PO
        //PO原则4 跳转或者进入新页面使用返回新的PO来模拟
        return new ContactPage(driver);
    }
}
