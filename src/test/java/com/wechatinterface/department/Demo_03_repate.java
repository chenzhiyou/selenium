package com.wechatinterface.department;

import com.wechatinterface.department.apiobject.DepartMentObject;
import com.wechatinterface.department.apiobject.TokenHelper;
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
    static int departmentID;
    static ArrayList<Integer> departmentIDList;
    @BeforeAll
    public static void getAccessToken(){
        accessToken = TokenHelper.getToken();
        //在初始化的时候，进行数据清理操作
    }

    @Order(1)
    @DisplayName("创建部门")
    @Test
    public void createDepart(){
        String name = "name" + FakerUtils.getTimeStamp();
        Response createResponse = DepartMentObject.createDepart(accessToken, name);
        departmentID = createResponse.path("id");
    }

    @Order(2)
    @DisplayName("更新部门")
    @Test
    public void updatedepart(){
        Response updateResponse = DepartMentObject.updateDepart(departmentID,accessToken);
    }

    @Order(3)
    @DisplayName("查询部门列表")
    @Test
    public void getDepartList(){
        Response getResponse= DepartMentObject.getDepart(accessToken, departmentID);
    }


    @Order(4)
    @DisplayName("删除部门列表")
    @Test
    public void deleteDepart(){
        Response deleteResponse = DepartMentObject.deleteDepart(accessToken, departmentID);

    }

}
