package com.apiobject.test;

import com.apiobject.framework.global.ApiLoader;
import com.apiobject.framework.steps.AssertModel;
import com.apiobject.framework.steps.StepModel;
import com.wechatinterface.department.Demo_01_base;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Test04_StepModleTest {
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_base.class);

    @BeforeAll
    public static void apiLoadertest() throws IOException {
        ApiLoader.load("src/test/resources/api");
        logger.info("debugger!");
    }

    @Test
    public void runTest(){
        //实参
        ArrayList<String> actualParamter = new ArrayList<>();
        actualParamter.add("wwdeaeef5a5bf87579");
        actualParamter.add("mLYMwTK2Ygar71W8yztfXYbKUji2POJ63u5pkNpBMkc");

        //断言
        ArrayList<AssertModel> asserts = new ArrayList<>();
        AssertModel assertModel = new AssertModel();
        assertModel.setActual("errcode");
        assertModel.setExpect("2");
        assertModel.setMatcher("equalTo");
        assertModel.setReason("getToken错误码校验01！");
        asserts.add(assertModel);

        //save
        HashMap<String, String> save = new HashMap<>();
        save.put("accesstoken", "access_token");
        //globalsave
        HashMap<String, String> globalsave = new HashMap<>();
        globalsave.put("accesstoken", "access_token");

        StepModel stepModel = new StepModel();
        stepModel.setApi("tokenhelper");
        stepModel.setAction("getToken");
        stepModel.setActualParameter(actualParamter);
        stepModel.setAsserts(asserts);
        stepModel.setSave(save);
        stepModel.setSaveGlobal(globalsave);
        stepModel.run(null);
        logger.info("Debugger");
    }

}
