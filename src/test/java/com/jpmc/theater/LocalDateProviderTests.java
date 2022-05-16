package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

public class LocalDateProviderTests {

    @Test
    void makeSureCurrentTime() {
        System.out.println("current time is - " + LocalDateProvider.singleton().currentDate());
    }

    @Test
    void testSingleton() {
        LocalDateProvider localDateProvider1 = LocalDateProvider.singleton();
        LocalDateProvider localDateProvider2 = LocalDateProvider.singleton();
        assertSame(localDateProvider1, localDateProvider2);
    }
}
