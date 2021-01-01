package com.frameWork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCase {
    public List<String> data;
    public List<HashMap<String, Object>> steps;
    public int index=0;

    //测试用例裂变，基于测试数据生成多个测试用例
    public List<TestCase> testcaseGenerate(){
        List<TestCase> testCaseList = new ArrayList<>();
        for (int i =0; i<data.size(); i++){
            TestCase testcaseNew = new TestCase();
            testcaseNew.index= i;
            testcaseNew.steps = steps;
            testcaseNew.data = data;
            testCaseList.add(testcaseNew);
        }
        return testCaseList;
    }

    // 替换yaml中的一些变量，负责结构需要递归处理
    public Object getValue(Map<String, Object> step, String key, Object defaultValue){
        return step.getOrDefault(key, defaultValue);
    }
    public Object getValue(Map<String, Object> step, String key){
        Object value = step.get(key);
        if (value instanceof String){
            //进行替换 todo:复杂结构支持
            return ((String) value).replace("${data}", data.get(index));
        }
        return value;
    }
    public void run(){

    }
}
