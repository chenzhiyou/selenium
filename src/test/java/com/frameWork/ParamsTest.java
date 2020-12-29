package com.frameWork;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import org.aspectj.util.FileUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

//    @ParameterizedTest
//    @ValueSource(strings = {"searche1", "shearch2"})
//    @MethodSource()
    //在MethodSource后面不添加方法时，直接寻找同名的
//    public void search(String keyword){
    // 测试数据-数据驱动的方法
//        ChromeDriver driver= new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.get("https://www.baidu.com");
//        driver.findElement(By.name("wd")).sendKeys(keyword);
//    }
    @ParameterizedTest
    @MethodSource()
    public void search(TestCase testCase){
        //解析测试步骤数据驱动
        System.out.println(testCase);
        // done:Runner引擎
        testCase.run();
    }

//    public static Stream<String> search(){
//        return Stream.of("apple", "banana");
//    }
    // 测试数据数据驱动
//    public static List<String> search() throws IOException {
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        TypeReference typeReference = new TypeReference<List<String>>(){
//        };
//
//        List<String> keywords= (List<String>) mapper.readValue(
//                ParamsTest.class.getResource("/framework/search.yaml"),typeReference);
//        return keywords;
//    }
    //测试步骤数据驱动
    public static Stream<TestCase> search() throws IOException{
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TestCase testCase = mapper.readValue(
                ParamsTest.class.getResourceAsStream("/framework/search.yaml"),TestCase.class
        );
        return Stream.of(testCase);
    }
}
