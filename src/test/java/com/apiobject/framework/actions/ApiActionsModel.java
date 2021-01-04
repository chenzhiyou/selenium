package com.apiobject.framework.actions;

import com.apiobject.framework.global.GlobalVariables;
import com.apiobject.utils.PlaceholderUtils;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.HashMap;

public class ApiActionsModel {
    //私有的变量，如果外部需要访问，需要设置getter和setter方法
    private String method = "get";
    private String url;
    private String body;
    private String contentType;
    private HashMap<String, String> query;
    private HashMap<String,String> headers;
    private String post;
    private String get;
    private Response response;
    private ArrayList<String> formalPara;
    private HashMap<String, String> actionVariables = new HashMap<>();

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public HashMap<String, String> getQuery() {
        return query;
    }

    public void setQuery(HashMap<String, String> query) {
        this.query = query;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getGet() {
        return get;
    }

    public void setGet(String get) {
        this.get = get;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public ArrayList<String> getFormalPara() {
        return formalPara;
    }

    public void setFormalPara(ArrayList<String> formalPara) {
        this.formalPara = formalPara;
    }

    public Response run(ArrayList<String> actualParameter){
        HashMap<String, String> finalQuery = new HashMap<>();
        String runBody = this.body;
        String runUrl = this.url;
        /**
         * 1、确定请求方法和URL
         */
        if(post !=null){
            runUrl = post;
            method = "post";
        }else if (get !=null){
            runUrl = get;
            method = "get";
        }
        /**
         * 2、请求参数、URL全局变量替换
         * PS：这里需要编写占位符工具类PlaceholderUtils
         */
        if(query !=null){
            finalQuery.putAll(PlaceholderUtils.resolveMap(query, GlobalVariables.getGlobalVariables()));
        }
        /**
         * body内部变量替换
         */
        runBody = PlaceholderUtils.resolveString(body, GlobalVariables.getGlobalVariables());
        //URL全局变量替换
        runUrl = PlaceholderUtils.resolveString(runUrl,GlobalVariables.getGlobalVariables());
        /**
         * 根据形参和实参构建内变量MAP
         */
        for(int index=0; index<formalPara.size(); index++){
            actionVariables.put(formalPara.get(index),actualParameter.get(index));
        }
    }






}
