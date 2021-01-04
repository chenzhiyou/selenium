package com.wechatinterface.department;

import com.wechatinterface.department.apiobject.DepartMentObject;
import com.wechatinterface.department.apiobject.TokenHelper;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 1、基础脚本，分别执行了创建、更新、查询、删除
 * 2、使用时间戳生成器，生成名字，确保数据的不重复性
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_03_repate {
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_base.class);
    static String accessToken;
    static int departmentID;

    @BeforeAll
    public static void getAccessToken(){
        accessToken = TokenHelper.getToken();
        //在初始化的时候，进行数据清理操作
        DepartMentObject.deleteDepartMethod(accessToken,1);
    }

    @Order(1)
    @DisplayName("创建部门")
//    @Test
    @ParameterizedTest
    @CsvFileSource(resources = "/data/createDepartment.csv",numLinesToSkip = 1)
    public void createDepart(String creatName, String returnCode){
        Response createResponse = DepartMentObject.createDepart(accessToken, creatName);
        departmentID = createResponse.path("id");
        assertEquals(returnCode, createResponse.path("errcode").toString());
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
