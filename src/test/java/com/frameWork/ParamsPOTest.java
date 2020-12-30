package com.frameWork;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;

public class ParamsPOTest {

    @ParameterizedTest
    @MethodSource()
    public void search(TestCase testCase){
        //解析测试步骤数据驱动
        System.out.println(testCase);
        // done:Runner引擎
        testCase.run();
    }

    //测试步骤数据驱动
    public static List<TestCase> search() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TestCase testCase = mapper.readValue(
                ParamsTest.class.getResourceAsStream("/framework/search_po_test.yaml"),
                TestCase.class
        );
        return testCase.testcaseGenerate();
    }
}
