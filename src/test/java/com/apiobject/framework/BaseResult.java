package com.apiobject.framework;

import io.restassured.response.Response;

/**
 * 用于保存结果的对象基类
 */
public class BaseResult {
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }


}
