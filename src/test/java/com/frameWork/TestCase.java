package com.frameWork;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestCase {
    public List<String> data;
    public List<HashMap<String, Object>> steps;
    private ChromeDriver driver;
    private WebElement currentElement;
    public int index=0;
    //测试用例裂变，基于测试数据生成多个测试用例
    public List<TestCase> testcaseGenerate(){
        List<TestCase> testCaseList = new ArrayList<>();
        for (int i =0; i<data.size(); i++){
            TestCase testcaseNew = new TestCase();
            testcaseNew.index=i;
            testcaseNew.steps = steps;
            testcaseNew.data = data;
            testCaseList.add(testcaseNew);
        }
        return testCaseList;
    }

    // 替换yaml中的一些变量，负责结构需要递归处理
    private Object getValue(Map<String, Object> step, String key, Object defaultValue){
        return step.getOrDefault(key, defaultValue);
    }
    private Object getValue(Map<String, Object> step, String key){
        Object value = step.get(key);
        if (value instanceof String){
            //进行替换 todo:复杂结构支持
            return ((String) value).replace("${data}", data.get(index));
        }
        return value;
    }

    public void run(){
        steps.forEach(step->{
            if(step.keySet().contains("chrome")){
                driver = new ChromeDriver();
            }
            if (step.keySet().contains("quit")){
                try {
                    Thread.sleep(5000);
                    driver.quit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(step.keySet().contains("implicitly_wait")){
                driver.manage().timeouts().implicitlyWait(
                        (int) getValue(step,"implicitly_wait", 5), TimeUnit.SECONDS);
            }
            if(step.keySet().contains("get")){
                driver.get(getValue(step,"get").toString());
            }
            if (step.keySet().contains("find")){
                ArrayList<By> bys = new ArrayList<>();
                ((HashMap<String, String>)getValue(step,"find")).entrySet().forEach(stringStringEntry -> {
                    if (stringStringEntry.getKey().contains("id")){
                        bys.add(By.id(stringStringEntry.getValue()));
                    }
                    if (stringStringEntry.getKey().contains("xpath")){
                        bys.add(By.xpath(stringStringEntry.getValue()));
                    }
                });
                currentElement = driver.findElement(bys.get(0));
            }
            if(step.keySet().contains("click")){
                currentElement.click();
            }
            if (step.keySet().contains("send_keys")){
                //TODO send_keys需要进行参数化
                currentElement.sendKeys(getValue(step,"send_keys").toString());
            }
        });
    }
}
