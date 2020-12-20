package com.web.selenium.testcase;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ContactPOTest {
    @Test
    public void testAddMember() throws IOException, InterruptedException {
        //打开界面
        //复用session
        MainPage mainPage = new MainPage();
        //跳转界面
        //进行搜索
        mainPage.addMember().addMember("小江山", "小江山", "18332553176");
        mainPage.addMember().searchDepart("销售部");
        String content = mainPage.addMember().getPartyInfo();
        assertTrue(content.contains("无任何成员"));
       // assert
    }
}
