package com.frameWork;

import org.junit.jupiter.api.Test;

public class SearchTest {
    @Test
    public void search(){
        MainPage main = new MainPage();
        SearchPage searchPage = main.search();
        searchPage.search("selemium");

    }
}
