package com.apiobject.test;

import com.apiobject.framework.api.ApiObjectModel;
import com.wechatinterface.department.Demo_01_base;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

public class Test02_ApiObjectModelTest {
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_base.class);

    @Test
    public void loadTest() throws IOException {
        ArrayList<String> actualParamter = new ArrayList<>();
        actualParamter.add("wwdeaeef5a5bf87579");
        actualParamter.add("mLYMwTK2Ygar71W8yztfXYbKUji2POJ63u5pkNpBMkc");

        ApiObjectModel apiObjectModel = ApiObjectModel.load("src/test/resources/api/tokenhelper.yaml");
        apiObjectModel.getActions().get("getToken").run(actualParamter);
    }
}
