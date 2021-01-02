package com.services;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.junit.jupiter.api.Test;
import org.junit.platform.console.shadow.picocli.CommandLine;

import java.io.IOException;


public class BMPTest {

    @Test
    public void bmp() throws IOException {
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(8081);
        int port = proxy.getPort();
        proxy.addResponseFilter((response, contents, messageInfo)->{
            if(messageInfo.getOriginalUrl().contains(".json")){
                // todo:json->hashmap ->rescue->hashmap->json
                String contextNew = contents.getTextContents().replaceAll("\"[^\"]x\"]", null);
                contents.setTextContents(contextNew);
            }
        });
        proxy.addRequestFilter((request, contents, messageInfo)->{
            request.setUri("/");
            return null;
        });

        System.in.read();
    }
//    public static void main(String[] args){
//        get("/hello",(req,res)->"hello world");
//        get("/proxy",(req,res)->{
//            BrowserMobProxy proxy = new BrowserMobProxyServer();
//            proxy.start(req.port());
//            return null;
//        });
//    }

}
