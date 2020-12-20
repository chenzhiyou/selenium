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
        ContactPage contactPage = mainPage.contact();
        contactPage.addMember("小江山", "小江山", "18332553176");
        contactPage.searchDepart("销售部");
        String content = contactPage.getPartyInfo();
        assertTrue(content.contains("无任何成员"));
       // assert
    }
}
