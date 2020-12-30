package com.frameWork;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

public class BasePage {
    //driver是一个实例对象
    WebDriver driver;
    HashMap<String, BasePage> pages = new HashMap<>();
    static BasePage instance= null;
    public BasePage(WebDriver driver){
        this.driver = driver;
    }
    public static BasePage getInstance(){
        if(instance == null){
            instance = new BasePage();
        }
        return instance;
    }
    public BasePage() {
    }

    public void click(By by){
        driver.findElement(by).click();
    }

    public void sendKeys(By by, String content){
        driver.findElement(by).sendKeys(content);
    }
    public void poInit(String name, String className) {
        try {
            // 动态创建类，并实例化为一个对象
            BasePage pageClass = (BasePage)Class.forName(name).newInstance();
            pages.put(name, pageClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }
    }
    public BasePage getPO(String name){
        return pages.get(name);
    }
    // 使用反射的技巧，让父类调用子类的方法
    public void stepRun(String method)  {
        Method methodJava = Arrays.stream(this.getClass().getMethods())
                .filter(m->m.getName().equals(method))
                .findFirst().get();
        // this.search() 动态执行方法
        try {
            methodJava.invoke(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}

