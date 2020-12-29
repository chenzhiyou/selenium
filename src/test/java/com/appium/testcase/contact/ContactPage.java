package com.appium.testcase.contact;

import com.appium.testcase.BasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class ContactPage extends BasePage {
    private By menu = By.id("com.tencent.wework:id/ie5");
    private By searchButton = By.id("com.tencent.wework:id/ie_");
    private By addDepartElement = By.xpath("//*[@text=''添加子部门]");
    private By editTextButton = By.className("android.widget.EditText");
    private By confirmButton = By.xpath("//*[@text='确定']");
    private By cancelButton = By.xpath("//*[@text='取消']");
    private By departName = By.xpath("//android.widget.ListView///android.widget.TextView");
    private By closeButton = By.xpath("//*[contains(@resource-id,'gpf') or contains(@resource-id, 'i6d')]");

    public ContactPage(AndroidDriver driver) {
        super(driver);
    }
    public ContactPage addDepart(String name){
        driver.findElement(menu).click();
        driver.findElement(addDepartElement).click();
        driver.findElement(editTextButton).sendKeys(name);
        driver.findElement(confirmButton).click();
        driver.findElement(closeButton).click();
        return this;
    }

    public ContactPage search(String keyword){
        driver.findElement(searchButton).click();
        driver.findElement(editTextButton).sendKeys(keyword+ Keys.ENTER);

        return this;
    }

    public String getCurrentDepartName(){
        // 修改为更加通用的查询验证方法
        StringBuilder contents = new StringBuilder();
        driver.findElements(departName).forEach(element->{
            contents.append(((WebElement)element).getText());
        });
        return contents.toString();
    }
}
