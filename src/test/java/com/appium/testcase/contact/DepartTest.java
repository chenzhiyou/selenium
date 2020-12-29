package com.appium.testcase.contact;

import com.appium.testcase.MainPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;


public class DepartTest {
    private  static MainPage main;

    @BeforeAll
    public static void beforeAll() throws MalformedURLException {
        //todo:clear all test data 可以结合接口、数据库恢复 环境恢复、UI自动化测试
        main = new MainPage();
    }

    @AfterAll
    public static void afterAll(){
        //清理工作？其实不是，afterall在测试用例出现一次，或者手工停止工作，没法暴躁此过程一定会被调用
        //afterAll可以放到beforeall中
        //套件的入口回退可以考虑使用
    }

    @Test
    @ValueSource(strings = {"xx", "中文", "a_b","a b"})
    public void add(String departName){
        String contents = main.contact().addDepart(departName).search(departName).getCurrentDepartName();
        assertTrue(contents.contains(departName));
    }

    @Test
    public void update(){

    }

    @BeforeEach
    public void beforeEach(){
        //进入入口
    }

    @AfterEach
    public void afterEach(){
        // 退到入口

    }
}
