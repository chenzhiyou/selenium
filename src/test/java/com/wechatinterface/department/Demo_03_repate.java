package com.wechatinterface.department;

import com.wechatinterface.department.apiobject.DepartMentObject;
import com.wechatinterface.department.apiobject.TokenHelper;
import com.wechatinterface.department.task.EvnHelperTask;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 1、基础脚本，分别执行了创建、更新、查询、删除
 * 2、使用时间戳生成器，生成名字，确保数据的不重复性
 */

@Epic("Epic企业微信接口测试用例")
@Feature("Feature部门相关功能测试")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_03_repate {
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_base.class);
    static String accessToken;
    static int departmentID;

    @BeforeAll
    public static void getAccessToken(){
        accessToken = TokenHelper.getToken();
        //在初始化的时候，进行数据清理操作
        EvnHelperTask.deleteDepartMethod(accessToken,1);
    }

    @Order(1)
    @DisplayName("创建部门")
    @Test
//    @ParameterizedTest
//    @CsvFileSource(resources = "/data/createDepartment.csv",numLinesToSkip = 1)
    @Story("Story 创建部门测试")
    public void createDepart(){
        String name = "name"+ FakerUtils.getTimeStamp();
        Response createResponse = DepartMentObject.createDepart(accessToken, name);
        departmentID = createResponse.path("id");
//        assertEquals(0, createResponse.path("errcode").toString());
    }

    @Order(2)
    @DisplayName("更新部门")
    @Test
    @Description("Description这个测试方法会测试修改部门的功能")
    public void updatedepart(){
        Response updateResponse = DepartMentObject.updateDepart(departmentID,accessToken);
    }

    @Order(3)
    @DisplayName("查询部门列表")
    @Test
    @Description("Description这个测试方法会测试查询部门的功能")
    @Story("Story查询部门测试")
    public void getDepartList(){
        Response getResponse= DepartMentObject.getDepart(accessToken, departmentID);
        //增加软断言
//        assertAll("查询返回值校验",
//                ()->assertEquals(0, getResponse.path("errcode").toString()));
    }


    @Order(4)
    @DisplayName("删除部门列表")
    @Test
    @Description("Description这个测试方法会测试删除部门的功能")
    @Story("Story删除部门测试")
    public void deleteDepart(){
        Response deleteResponse = DepartMentObject.deleteDepart(accessToken, departmentID);

    }

}
