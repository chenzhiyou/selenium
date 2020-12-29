package com.frameWork;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParamsTest {
    @ParameterizedTest
    @MethodSource("stringProvider")
    public void testWithExplicitLocalMethodSource(String argument){
        //todo :测试步骤
        // todo:测试数据
        //TODO：断言
        assertNotNull(argument);
    }

    public static Stream<String> stringProvider(){
        return Stream.of("apple", "banana");
    }

    @ParameterizedTest
    @ValueSource(strings = {"searche1", "shearch2"})
    public void search(String keyword){
        ChromeDriver driver= new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.baidu.com");
        driver.findElement(By.name("wd")).sendKeys(keyword);


    }

}
