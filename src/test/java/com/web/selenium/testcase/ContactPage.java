package com.web.selenium.testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.util.Random;

public class ContactPage extends BasePage {
    // PO2 原则2：不要暴露页面细节
    private By parterInfo = By.cssSelector(".js_party_info");

    public ContactPage(WebDriver driver) {
        //保存driver到自己的实例
        super(driver);
    }
    //PO原则6 添加成功的时候与添加失败返回的页面是不同的，需要封装为不同的方法
    // 添加成员成功
    public ContactPage addMember(String username, String acctid, String mobile){
        sendKeys(By.name("username"),username);
        sendKeys(By.id("memberAdd_english_name"), acctid);
        sendKeys(By.name("mobile"),mobile);
        Random r = new Random(1);
        int num = r.nextInt(100);
        sendKeys(By.id("memberAdd_acctid"),acctid);
        driver.findElements(By.xpath("//a[@class='qui_btn ww_btn ww_btn_Blue js_btn_continue']")).get(1).click();
        return this;
    }

    //添加成员失败
    public ContactPage addMemberFail(String username, String acctid, String mobile){
        return this;
    }
    //PO原则5 不用实现所有的方法，按需封装

    //创建部门
    public ContactPage addDepart(String departName){
        click(By.cssSelector(".member_colLeft_top_addBtn"));
        click(By.linkText("添加部门"));
        sendKeys(By.name("name"),departName);
        click(By.cssSelector(".js_parent_party_name"));
        driver.findElements(By.linkText("测试")).get(1).click();
        click(By.linkText("确定"));
        return this;
    }
    public ContactPage searchDepart(String departName) throws InterruptedException {
        // PO原则1 用公共方法代表页面所提供的功能
        //PO原则3 通常不要再PO方法内添加断言
        sendKeys(By.id("memberSearchInput"),departName);
        Thread.sleep(4000);
        click(By.cssSelector(".member_colRight_cnt_main"));
        return this;
    }
//  更新部门信息
    public ContactPage updataDepart(String departName){
        //点击哈哈后的按钮
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'哈哈')]")));
        click(By.xpath("//a[contains(text(),'哈哈')]"));
        click(By.xpath("//a[contains(text(),'哈哈')]/span"));
//        driver.findElements(By.cssSelector(".jstree-contextmenu-hover")).get(2).click();
        click(By.linkText("修改名称"));
        sendKeys(By.name("name"), departName);
        click(By.linkText("保存"));
        return this;
    }
// 删除部门信息
    public ContactPage deleteDepart(){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'哈哈1')]")));
        click(By.xpath("//a[contains(text(),'哈哈1')]"));
        click(By.xpath("//a[contains(text(),'哈哈1')]/span"));
        click(By.linkText("删除"));
        click(By.linkText("确定"));
        return this;
    }

    //获取搜索部门后的结果信息
    public String getPartyInfo(){
        String context = driver.findElement(parterInfo).getText();
        System.out.println(context);
        return context;
    }
}
