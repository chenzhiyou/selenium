package com.wechatinterface.department.apiobject;

import static io.restassured.RestAssured.given;

public class TokenHelper {
    public static String getToken(){
        String accessToken = given()
                .params("corpid","wwdeaeef5a5bf87579","corpsecret","mLYMwTK2Ygar71W8yztfXYbKUji2POJ63u5pkNpBMkc")
                .get(" https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .extract().response().path("access_token");
        return accessToken;
    }
}
