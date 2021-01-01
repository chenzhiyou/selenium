package com.frameWork;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BasePage {
    static BasePage instance= null;
    //driver是一个实例对象
    WebDriver driver;
    private SeleniumTestCase seleniumTestCase;
    HashMap<String, BasePage> pages = new HashMap<>();
    HashMap<String, List<HashMap<String, Object>>> yamlSource = new HashMap<>();
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
//            BasePage pageClass = (BasePage)Class.forName(name).newInstance();
            BasePage pageClass = new BasePage();
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference<HashMap<String,List<HashMap<String,Object>>>> typeReference =
                    new TypeReference<HashMap<String, List<HashMap<String, Object>>>>() {};
            pageClass.yamlSource = mapper.readValue(ParamsTest.class.getResourceAsStream(String.format("/framework/%s",className)),
                    typeReference);
            pages.put(name, pageClass);
            pageClass.stepRun("init");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public BasePage getPO(String name){
        return pages.get(name);
    }
    // 使用反射的技巧，让父类调用子类的方法
    // 反射找Java方法
//    public void stepRun(String method)  {
//        Method methodJava = Arrays.stream(this.getClass().getMethods())
//                .filter(m->m.getName().equals(method))
//                .findFirst().get();
//        // this.search() 动态执行方法
//        try {
//            methodJava.invoke(this);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//    }
    public void stepRun(String method){
        List<HashMap<String, Object>> steps = yamlSource.get(method);
        seleniumTestCase = new SeleniumTestCase();
        seleniumTestCase.steps = steps;
        seleniumTestCase.data = Arrays.asList("");
        seleniumTestCase.run();
    }


}

