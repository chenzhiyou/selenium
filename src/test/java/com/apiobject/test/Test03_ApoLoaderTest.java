package com.apiobject.test;

import com.apiobject.framework.api.ApiObjectModel;
import com.apiobject.framework.global.ApiLoader;
import com.wechatinterface.department.Demo_01_base;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test03_ApoLoaderTest {
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_base.class);

    @BeforeAll
    public static void apiLoadertest() throws IOException {
        ApiLoader.load("src/test/resources/api");
        logger.info("debugger!");
    }

    @Test
    public void getActionTest(){
        ArrayList<String> actualParamter = new ArrayList<>();
        actualParamter.add("wwdeaeef5a5bf87579");
        actualParamter.add("mLYMwTK2Ygar71W8yztfXYbKUji2POJ63u5pkNpBMkc");
        ApiLoader.getAction("tokenhelper", "getToken").run(actualParamter);

    }
}
