package com.services;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DemoTest2 {

    @Test
    public void testJson(){
        given()
                .get("http://127.0.0.1:8000/testRestDemo.json")
                .then()
                //在返回的结构体是数组时，可以使用hasItems进行断言
                .body("store.book.category",hasItems("reference","fiction"))
                //判断第一本书的作者
                .body("store.book[0].author",equalTo("Nigel Rees"))
                .body("store.book[-1].author",equalTo("J. R. R. Tolkien"))
                .body("store.book.findAll { book -> book.price == 8.95f}.price",equalTo(8.95))
                ;
    }

    @Test
    public void testXML(){
        given()
                .get("http://127.0.0.1:8000/testRestDemo.xml")
                .then()
                .body("shopping.category[0].item[0].name",equalTo("Chocolate"))
                //断言item的数量
                .body("shopping.category.item.size()",equalTo(5))
                //支持复杂表达式
                .body("shopping.category.findAll { it.@type == 'groceries' }.size()",equalTo(1))
                .body("shopping.category.item.findAll {it.price == 20}.name",equalTo("Coffee"))
                //支持**表达式
                .body("**.findAll {it.price == 20}.name",equalTo("Coffee"))
                ;
    }
}
