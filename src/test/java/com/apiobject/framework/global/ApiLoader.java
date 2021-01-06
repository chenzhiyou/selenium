package com.apiobject.framework.global;

import com.apiobject.framework.api.ApiObjectModel;

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
}

