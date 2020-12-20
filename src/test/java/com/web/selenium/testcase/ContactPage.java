package com.web.selenium.testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class ContactPage extends BasePage {

    public ContactPage(WebDriver driver) {
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
        snedKeys(By.id("memberSearchInput"),departName);
//        Thread.sleep(2000);
//        String context = driver.findElement(By.cssSelector(".js_party_info")).getText();
        //        在进行页面元素获取时，可能没有出现页面的元素，可以添加对一个元素的点击，利用隐式等待，后，再次获取元素
        click(By.cssSelector(".ww_icon ww_icon_AddMember"));
//        context = driver.findElement(By.cssSelector(".js_party_info")).getText();
        return this;
    }

    //获取搜索部门后的结果信息
    public String getPartyInfo(){
        String context = driver.findElement(By.cssSelector(".js_party_info")).getText();
        System.out.println(context);
        return context;
    }
}
