package com.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetTest {
    public static String access_token;
    @BeforeAll
    public static void GetMethod(){
        access_token = given()
                .params("corpid","wwdeaeef5a5bf87579","corpsecret","YWQbt8zkVblEPzhuKAfk6XEevGr-efC_9DgenMswkm4")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .extract().response().path("access_token");
        System.out.println(access_token);
    }

    @Test
    public void PostMethod(){
        given()
                .contentType("application/json;charset=utf-8")
                .body("{\n" +
                        "   'touser' : \"@all\",\n" +
                        "   \"msgtype\" : \"text\",\n" +
                        "   \"agentid\" : 1000002,\n" +
                        "   \"text\" : {\n" +
                        "       \"content\" : \"你的快递已到，请携带工卡前往邮件中心领取。\\n出发前可查看<a href=\\\"http://work.weixin.qq.com\\\">邮件中心视频实况</a>，聪明避开排队。\"\n" +
                        "   },\n" +

                        "}")
                .queryParams("access_token",access_token)
                .post("https://qyapi.weixin.qq.com/cgi-bin/message/send")
                .then()
                .log().all();

    }
}
