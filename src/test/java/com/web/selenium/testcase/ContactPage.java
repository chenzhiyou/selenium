package com.web.selenium.testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class ContactPage extends BasePage {

    public ContactPage(WebDriver driver) {
        //保存driver到自己的实例
        super(driver);
    }

    public ContactPage addMember(String username, String acctid, String mobile){
        snedKeys(By.name("username"),username);
        snedKeys(By.id("memberAdd_english_name"), acctid);
        snedKeys(By.name("mobile"),mobile);
        Random r = new Random(1);
        int num = r.nextInt(100);
        snedKeys(By.id("memberAdd_acctid"),acctid);
        driver.findElements(By.xpath("//a[@class='qui_btn ww_btn ww_btn_Blue js_btn_continue']")).get(1).click();
        return this;
    }

    public ContactPage searchDepart(String departName){
        // PO原则1 用公共方法代表页面所提供的功能
        //PO原则3 通常不要再PO方法内添加断言
        snedKeys(By.id("memberSearchInput"),departName);
        click(By.cssSelector(".ww_icon ww_icon_AddMember"));
        return this;
    }

    //获取搜索部门后的结果信息
    public String getPartyInfo(){
        String context = driver.findElement(By.cssSelector(".js_party_info")).getText();
        System.out.println(context);
        return context;
    }
}
