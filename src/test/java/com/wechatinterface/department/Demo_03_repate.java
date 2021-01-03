package com.wechatinterface.department;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

/**
 * 1、基础脚本，分别执行了创建、更新、查询、删除
 * 2、使用时间戳生成器，生成名字，确保数据的不重复性
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_03_repate {
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_base.class);
    static String accessToken;
    static String departmentID;
    static ArrayList<Integer> departmentIDList;
    @BeforeAll
    public static void getAccessToken(){
        accessToken = given()
                .params("corpid","wwdeaeef5a5bf87579","corpsecret","mLYMwTK2Ygar71W8yztfXYbKUji2POJ63u5pkNpBMkc")
                .get(" https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .extract().response().path("access_token");
        //在初始化的时候，进行数据清理操作
        clearDepartment();
    }

    @Order(1)
    @DisplayName("创建部门")
    @Test
    public void createDepart(){
        String name = "name"+ FakerUtils.getTimeStamp();
        String body="{\n" +
                "   \"name\": \""+name+"\",\n" +
                "   \"parentid\": 1,\n" +
                "}\n";
        Response response = given()
                .contentType("application/json")
                .queryParams("access_token",accessToken)
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then()
                .log().all().extract().response();
        departmentID = response.path("id").toString();
    }

    @Order(2)
    @DisplayName("更新部门")
    @Test
    public void updatedepart(){
        String body ="{\n" +
                "   \"id\":"+ departmentID+",\n" +
                "}\n";
        Response response = given()
                .queryParams("access_token",accessToken)
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then()
                .log().all()
                .extract().response();
    }

    @Order(3)
    @DisplayName("查询部门列表")
    @Test
    public void getDepartList(){
        Response response = given()
                .param("access_token",accessToken)
                .param("id",departmentID)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all()
                .extract().response();
    }
    //封装查询方法
    public static ArrayList<Integer> getDepartListMethod(){
        Response response = given()
                .param("access_token",accessToken)
                .param("id",1)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all()
                .extract().response();
        departmentIDList = response.path("department.id");
        System.out.println(departmentIDList);
        return departmentIDList;
    }

    @Order(4)
    @DisplayName("删除部门列表")
    @Test
    public void deleteDepart(){
        Response response = given()
                .contentType("application/json")
                .param("access_token",accessToken)
                .param("id",departmentID)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then()
                .log().all()
                .extract().response();

    }
    //封装删除方法
    public static void deleteDepartMethod(int id){
        Response response = given()
                .contentType("application/json")
                .param("access_token",accessToken)
                .param("id",id)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then()
                .log().all()
                .extract().response();

    }

    //封装数据清理方法
    public static void clearDepartment(){
        departmentIDList = getDepartListMethod();
        for(int deparmentID:departmentIDList){
            if(deparmentID==1){
                continue;
            }else{
                deleteDepartMethod(deparmentID);
            }
        }
    }
}
