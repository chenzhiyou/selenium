package com.wechatinterface.department.apiobject;

import io.restassured.response.Response;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class DepartMentObject {
    public static Response createDepart(String accessToken, String name){
        String body="{\n" +
                "   \"name\": \""+name+"\",\n" +
                "   \"parentid\": 1,\n" +
                "}\n";
        Response createResponse = given()
                .contentType("application/json")
                .queryParams("access_token",accessToken)
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then()
                .log().all().extract().response();
        return createResponse;
    }

    public static Response updateDepart(int departmentID, String accessToken){
        String body ="{\n" +
                "   \"id\":"+ departmentID+",\n" +
                "}\n";
        Response updateResponse = given()
                .queryParams("access_token",accessToken)
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then()
                .log().all()
                .extract().response();
        return updateResponse;
    }

    public static Response getDepart(String accessToken, int departmentID){
        Response getResponse = given()
                .param("access_token",accessToken)
                .param("id",departmentID)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all()
                .extract().response();
        return getResponse;
    }

    public static Response deleteDepart(String accessToken, int departmentID){
        Response deleteResponse = given()
                .contentType("application/json")
                .param("access_token",accessToken)
                .param("id",departmentID)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then()
                .log().all()
                .extract().response();
        return deleteResponse;
    }

//    public static void deleteDepartMethod( String accessToken){
//        ArrayList<Integer> departmentIDList = (ArrayList<Integer>) getDepart(accessToken,1);
//        for(int deparmentID:departmentIDList){
//            if(deparmentID==1){
//                continue;
//            }else{
//                deleteDepart(accessToken,deparmentID);
//            }
//        }
//
//    }
}
