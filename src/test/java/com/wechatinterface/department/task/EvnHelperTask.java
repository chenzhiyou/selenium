package com.wechatinterface.department.task;

import com.wechatinterface.department.apiobject.DepartMentObject;

import java.util.ArrayList;

public class EvnHelperTask {
    public static void deleteDepartMethod( String accessToken, int departmentID){
        ArrayList<Integer> departmentIDList = DepartMentObject.getDepart(accessToken,departmentID).path("department.id");
        System.out.println(departmentIDList);
        for(int deparmentID:departmentIDList){
            if(deparmentID==1){
                continue;
            }else{
                DepartMentObject.deleteDepart(accessToken,deparmentID);
            }
        }
    }
}
