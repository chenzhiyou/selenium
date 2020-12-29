package com.frameWork;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParamsTest {
    @ParameterizedTest
    @MethodSource("stringProvider")
    public void testWithExplicitLocalMethodSource(String argument){
        assertNotNull(argument);
    }

    public static Stream<String> stringProvider(){
        return Stream.of("apple", "banana");
    }

}
