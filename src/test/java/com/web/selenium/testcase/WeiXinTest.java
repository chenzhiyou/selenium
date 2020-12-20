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

import static org.junit.jupiter.api.Assertions.*;

public class WeiXinTest {
    public static WebDriver driver;

    @BeforeAll
    public static void initData() throws InterruptedException, IOException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        File file = new File("cookies.yaml");
        if(file.exists()) {
            // 使用yaml文件中的cookies信息进行登录，登录作为前置条件，移动到beforeall中进行操作
            driver.get("https://work.weixin.qq.com/wework_admin/frame");
            Thread.sleep(4000);
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

//    需要登录的时候执行登录的方法
    public static void neddLogin() throws IOException {
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

    @Test
    public void AddMemberTest() throws IOException, InterruptedException {
        //添加新的成员
//        driver.findElement(By.xpath("//span[@class='ww_indexImg ww_indexImg_AddMember']")).click();
        driver.findElement(By.cssSelector("[node-type='addmember']")).click();
        driver.findElement(By.name("username")).sendKeys("小江山");
        driver.findElement(By.id("memberAdd_english_name")).sendKeys("小江山");
        driver.findElement(By.name("mobile")).sendKeys("18332553177");
        Random r = new Random(1);
        int num = r.nextInt(100);
        String acctid = "admin" + num;
        driver.findElement(By.id("memberAdd_acctid")).sendKeys(acctid);
        driver.findElements(By.xpath("//a[@class='qui_btn ww_btn ww_btn_Blue js_btn_continue']")).get(1).click();
    }
    @Test
    public void AddMember2Test() throws IOException, InterruptedException {
        //使用简单封装后的方法进行添加成员的测试
//        driver.findElement(By.xpath("//span[@class='ww_indexImg ww_indexImg_AddMember']")).click();
        click(By.cssSelector("[node-type='addmember']"));
        snedKeys(By.name("username"),"小江山");
        snedKeys(By.id("memberAdd_english_name"), "小江山");
        snedKeys(By.name("mobile"),"18332553176");
        Random r = new Random(1);
        int num = r.nextInt(100);
        String acctid = "admin" + num;
        snedKeys(By.id("memberAdd_acctid"),acctid);
        driver.findElements(By.xpath("//a[@class='qui_btn ww_btn ww_btn_Blue js_btn_continue']")).get(1).click();
    }

    @Test
    public void departementSearchTest() throws InterruptedException {
//        部门搜索测试用例
        click(By.cssSelector("[href='#contacts']"));
        snedKeys(By.id("memberSearchInput"),"测试");
//        Thread.sleep(2000);
        String context = driver.findElement(By.cssSelector(".js_party_info")).getText();
        //        在进行页面元素获取时，可能没有出现页面的元素，可以添加对一个元素的点击，利用隐式等待，后，再次获取元素
        click(By.cssSelector(".ww_icon ww_icon_AddMember"));
        context = driver.findElement(By.cssSelector(".js_party_info")).getText();
        assertTrue(context.contains("当前部门无任何成员"));
    }

    @Test
//    public void departementAddTest(){
//        click(By.cssSelector("[href='#contacts']"));
//        click(By.xpath("//a[@class='member_colLeft_top_addBtnWrap js_create_dropdown']"));
//        click(By.xpath("//a[@class='js_create_party']"));
//        snedKeys(By.name("name"),"测试部门");
//
//
//    }
//    进行简单的封装
    public void click(By by){
        driver.findElement(by).click();
    }

    public void snedKeys(By by, String content){
        driver.findElement(by).sendKeys(content);
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }
}
