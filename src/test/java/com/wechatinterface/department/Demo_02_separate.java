package com.wechatinterface.department;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class Demo_02_separate {
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_base.class);
    static String accessToken;
    static String departmentID;
    @BeforeAll
    public static void getAccessToken(){
        accessToken = given()
                .params("corpid","wwdeaeef5a5bf87579","corpsecret","mLYMwTK2Ygar71W8yztfXYbKUji2POJ63u5pkNpBMkc")
                .get(" https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .extract().response().path("access_token");
    }

    @Order(1)
    @DisplayName("创建部门")
    @Test
    public void createDepart(){
        String createBody="{\n" +
                "   \"name\": \"广州研发中心10\",\n" +
                "   \"parentid\": 1,\n" +
                "}\n";
        Response createResponse = given()
                .contentType("application/json")
                .queryParams("access_token",accessToken)
                .body(createBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then()
                .log().all().extract().response();
        departmentID = createResponse.path("id").toString();
    }

    @Order(2)
    @DisplayName("更新部门")
    @Test
    public void updatedepart(){
        String updateBody ="{\n" +
                "   \"id\":"+ departmentID+",\n" +
                "}\n";
        Response updateResponse = given()
                .queryParams("access_token",accessToken)
                .body(updateBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then()
                .log().all()
                .extract().response();
    }

    @Order(3)
    @DisplayName("查询部门列表")
    @Test
    public void getDepartList(){
        Response getResponse = given()
                .param("access_token",accessToken)
                .param("id",departmentID)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all()
                .extract().response();
    }

    @Order(4)
    @DisplayName("删除部门列表")
    @Test
    public void deleteDepart(){

        Response deleteResponse = given()
                .contentType("application/json")
                .param("access_token",accessToken)
                .param("id",departmentID)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then()
                .log().all()
                .extract().response();

    }
}
