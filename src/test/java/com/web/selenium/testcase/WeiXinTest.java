package com.web.selenium.testcase;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WeiXinTest {
    public static WebDriver driver;

    @BeforeAll
    public static void initData(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void loginTest() throws IOException {
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        try {
            Thread.sleep(20000);
//          将读取大到的cookies信息存储起来
            Set<Cookie> cookies  = driver.manage().getCookies();
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.writeValue(new File("cookies.yaml"), cookies);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    // 使用yaml文件中的cookies信息进行登录
    @Test
    public void loginedTest() throws IOException, InterruptedException {
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        Thread.sleep(4000);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference<List<HashMap<String, Object>>> typeReference = new TypeReference<List<HashMap<String, Object>>>(){};
        List<HashMap<String, Object>> cookies = mapper.readValue(new File("cookies.yaml"), typeReference);
        System.out.println(cookies);
        cookies.forEach(cookieMap->{
            driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(),cookieMap.get("value").toString()));
        });
//      刷新界面
        driver.navigate().refresh();
        Thread.sleep(4000);
        //添加新的成员
        driver.findElement(By.xpath("//span[@class='ww_indexImg ww_indexImg_AddMember']")).click();
        driver.findElement(By.id("username")).sendKeys("小江山");
        driver.findElement(By.id("memberAdd_english_name")).sendKeys("小江山");
        Random r = new Random(1);
        int num = r.nextInt(100);
        String acctid = "admin" + num;
        driver.findElement(By.id("memberAdd_acctid")).sendKeys(acctid);
        driver.findElements(By.xpath("//a[@class='qui_btn ww_btn ww_btn_Blue js_btn_continue']")).get(1).click();
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }
}
