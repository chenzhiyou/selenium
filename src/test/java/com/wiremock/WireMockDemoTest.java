package com.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;


public class WireMockDemoTest {
    static WireMockServer wireMockServer;

    @BeforeAll
    public static void initData(){
        // wiremock初始化
        wireMockServer = new WireMockServer(wireMockConfig().port(8089));
        wireMockServer.start();
        configureFor("localhost",8089);
    }

    @Test
    public void stubMock(){
        try {
            stubFor(get(urlEqualTo("/my/resource"))
                    .withHeader("Accept", equalTo("text/xml"))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "text/xml")
                            .withBody("<response>Some content</response>")));
            Thread.sleep(500000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void easy_mock(){
        try {
            stubFor(get(urlEqualTo("/my/resource"))
                    .withHeader("Accept", equalTo("text/xml"))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "text/xml")
                            .withBody("<response>ceshiren</response>")));
            Thread.sleep(10000);
            reset();
            stubFor(get(urlEqualTo("/my/resource"))
                    .withHeader("Accept", equalTo("text/xml"))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "text/xml")
                            .withBody("<response>测试人</response>")));
            Thread.sleep(500000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 借助proxy读取本地文件进行mock数据，功能类似于Charles中的maplocal功能
    @Test
    public void proxyMockTest(){
        try {
            //监听的网站的配置
            stubFor(get(urlMatching(".*")).atPriority(10)
                    .willReturn(aResponse().proxiedFrom("https://ceshiren.com")));

            //将监听到的内容，替换为本地的文件数据
            stubFor(get(urlEqualTo("/categories_and_latest")).atPriority(1)
                    .willReturn(aResponse().withBody(Files.readAllBytes(Paths.get(WireMockDemoTest.class.getResource("/framework/mock.json").getPath())))));
            Thread.sleep(500000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
