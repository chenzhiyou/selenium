package com.apiobject.test;

import com.apiobject.framework.api.ApiObjectModel;
import com.apiobject.framework.global.ApiLoader;
import com.wechatinterface.department.Demo_01_base;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class Test03_ApoLoaderTest {
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_base.class);

    @Test
    public void apiLoadertest() throws IOException {
        ApiLoader.load("src/test/resources/api");
        logger.info("debugger!");
    }
}
