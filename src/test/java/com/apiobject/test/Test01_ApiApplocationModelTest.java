package com.apiobject.test;

import com.apiobject.framework.actions.ApiActionsModel;
import com.apiobject.framework.global.GlobalVariables;
import com.wechatinterface.department.Demo_01_base;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class Test01_ApiApplocationModelTest {
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_base.class);
    @Test
    public void runTest(){
        ArrayList<String> actualParamter = new ArrayList<>();
        actualParamter.add("wwdeaeef5a5bf87579");
        actualParamter.add("mLYMwTK2Ygar71W8yztfXYbKUji2POJ63u5pkNpBMkc");

        ApiActionsModel apiActionsModel = new ApiActionsModel();
        apiActionsModel.setUrl("https://qyapi.weixin.qq.com/cgi-bin/${x}");
        HashMap<String, String> globalVariables = new HashMap<>();

        globalVariables.put("x", "gettoken");
        GlobalVariables.setGlobalVariables(globalVariables);

        ArrayList<String> formalParameter = new ArrayList<>();
        formalParameter.add("corpid");
        formalParameter.add("corpsecret");
        apiActionsModel.setFormalParam(formalParameter);

        HashMap<String, String> query = new HashMap<>();
        query.put("corpid","${corpid}");
        query.put("corpsecret", "${corpsecret}");
        apiActionsModel.setQuery(query);

        Response response = apiActionsModel.run(actualParamter);
    }
}
