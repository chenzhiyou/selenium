package com.services;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.junit.jupiter.api.Test;

public class BMPTest {

    @Test
    public void bmp(){
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0);
        int port = proxy.getPort();
    }

}
