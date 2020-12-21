package com.web.selenium.testcase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ContactPOTest {
    private static MainPage mainPage;
    private static ContactPage contactPage;

    @BeforeAll
    public static void setUp() throws IOException, InterruptedException {
        mainPage = new MainPage();
        contactPage = mainPage.contact();
    }
    //添加成员
    @Test
    public void testAddMember() {
        contactPage.addMember("小江山", "小江山", "18332553176");

    }
    //搜索部门
    @Test
    public void testSearchDepart() throws InterruptedException {
        contactPage.searchDepart("销售部");
        String content = contactPage.getPartyInfo();
        assertTrue(content.contains("无任何成员"));
    }

    //添加部门测试
    @Test
    public void testAddDepart() throws InterruptedException {
        String department = "哈哈";
        contactPage.addDepart(department);
        contactPage.searchDepart(department);
        String content = contactPage.getPartyInfo();
        assertTrue(content.contains(department));
    }

    // 更新部门信息
    @Test
    public void testUpdateDepart() throws InterruptedException {
        String department = "哈哈1";
        contactPage.updataDepart(department);
        contactPage.searchDepart(department);
        String content = contactPage.getPartyInfo();
        assertTrue(content.contains(department));
    }
    // 删除部门信息
    @Test
    public void testDeleteDepart() throws InterruptedException {
        String department = "哈哈1";
        contactPage.deleteDepart();
        contactPage.searchDepart(department);
        String content = contactPage.getPartyInfo();
        assertTrue(!(content.contains(department)));
    }
}
