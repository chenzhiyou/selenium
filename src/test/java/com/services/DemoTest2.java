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
}
