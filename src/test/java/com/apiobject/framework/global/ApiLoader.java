package com.apiobject.framework.global;

import com.apiobject.framework.actions.ApiActionsModel;
import com.apiobject.framework.api.ApiObjectModel;
import com.wechatinterface.department.Demo_01_base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 接口对象加载器
 */
public class ApiLoader {
    /**
     * 加载所有api Object对象，保存到本列表中
     */
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_base.class);
    private static List<ApiObjectModel> apis = new ArrayList<>();

    public static void load(String dir){
        //遍历文件
        Arrays.stream(new File(dir).list()).forEach(path->{
            try {
                apis.add(ApiObjectModel.load(dir +"/"+ path));
                System.out.println(apis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static ApiActionsModel getAction(String apiName, String actionName){
        final ApiActionsModel[] apiActionsModel = {new ApiActionsModel()};
        apis.stream().filter(api->api.getName().equals(apiName)).forEach(api->{
            apiActionsModel[0] = api.getActions().get(actionName);
        });
        if(apiActionsModel[0]!=null){
            return apiActionsModel[0];
        }else {
            logger.info("没有找到接口对象："+apiName +"中的action" +actionName);
        }
        return null;
    }
}

