package com.apiobject.test;

import com.apiobject.framework.global.ApiLoader;
import com.apiobject.framework.testcase.ApiTestCaseModel;
import com.wechatinterface.department.Demo_01_base;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Test05_ApiTestCaseTest {
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_base.class);

    @BeforeAll
    public static void apiLoadertest() throws IOException {
        ApiLoader.load("src/test/resources/api");
        logger.info("debugger!");
    }

    @Test
    public void loadTest() throws IOException {
        ApiTestCaseModel apiTestCaseModel = ApiTestCaseModel.load("src/test/resources/testcaase/creatdepartment.yaml");
        logger.debug("debugger");

    }

    @Test
    public void runTest() throws IOException {
        ApiTestCaseModel apiTestCaseModel = ApiTestCaseModel.load("src/test/resources/testcaase/creatdepartment.yaml");
        apiTestCaseModel.run();
    }


}
