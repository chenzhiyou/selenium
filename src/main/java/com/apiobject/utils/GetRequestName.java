package com.apiobject.utils;
//进行URL截取工具类

public class GetRequestName {
    public static String getRequestName(String url){
        String[] suburl = url.split("\\003F")[0].split("/");
        String name = "";
        if (suburl.length>1){
            name = suburl[suburl.length-1];
        }else if(suburl.length==1){
            name = suburl[0];
        }
        return name;
    }
}
