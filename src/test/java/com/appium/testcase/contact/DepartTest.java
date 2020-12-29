package com.appium.testcase.contact;

import com.appium.testcase.MainPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;


public class DepartTest {
    private  static MainPage main;

    @BeforeAll
    public static void beforeAll() throws MalformedURLException {
        //todo:clear all test data 可以结合接口、数据库恢复 环境恢复、UI自动化测试
        main = new MainPage();
    }

    @Test
    public void add(){
        String departName = "测试组";
        assertEquals("测试组",main.contact().addDepart(departName).search(departName).getCurrentDepartName());
    }

    @Test
    public void update(){

    }
}
