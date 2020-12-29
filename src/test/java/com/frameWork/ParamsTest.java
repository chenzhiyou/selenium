package com.frameWork;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import org.aspectj.util.FileUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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

    @ParameterizedTest
//    @ValueSource(strings = {"searche1", "shearch2"})
    @MethodSource()
    //在MethodSource后面不添加方法时，直接寻找同名的
    public void search(String keyword){
        ChromeDriver driver= new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.baidu.com");
        driver.findElement(By.name("wd")).sendKeys(keyword);
    }

//    public static Stream<String> search(){
//        return Stream.of("apple", "banana");
//    }
    public static List<String> search() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        String path = ParamsTest.class.getResource("/framework/search.yaml").getPath();
        System.out.println(path);
        File file = new File(
                String.valueOf(Thread.currentThread().
                        getContextClassLoader().
                        getResource("/framework/search.yaml")));
        TypeReference typeReference = new TypeReference<List<String>>(){
        };
        System.out.println(FileUtil.canReadFile(file));
        List<String> x = new ArrayList<String>();
        x.add("xx");
        x.add("ddd");
        System.out.println(mapper.writeValueAsString(x));
        List<String> keywords= (List<String>) mapper.readValue(
                ParamsTest.class.getResource("/framework/search.yaml"),typeReference);
        return keywords;
    }






}
