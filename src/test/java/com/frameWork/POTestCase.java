package com.frameWork;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class POTestCase extends TestCase{
    private ChromeDriver driver;
    private WebElement currentElement;
    private MainPage mainPage;

    public void run(){
        steps.forEach(step ->{
            String key = step.keySet().iterator().next();
            if(key.contains("init")){
                ArrayList<String> value = (ArrayList<String>) getValue(step,"init");
                BasePage.getInstance().poInit(value.get(0), value.get(1));

            }

            if(key.contains(".")){
                String[] objectMethod = key.split("\\.");
                String object = objectMethod[0];
                String method = objectMethod[1];
                //解决找方法的问题
                BasePage.getInstance().getPO(object).stepRun(method);
            }
        });
    }

}
