package com.jpmc.theater;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jpmc.theater.Theater.PrintFormat;

public class TheaterTests {
    @Test
    void totalFeeForCustomer() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
//        System.out.println("You have to pay " + reservation.getTotalFee());
        assertEquals(reservation.totalFee(), 50);
    }

    @Test
    void printMovieSchedule() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule(PrintFormat.TEXT);
    }

    @Test
    void printMovieScheduleInJson() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule(PrintFormat.JSON);
    }

    @Test
    void invalidSequence() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, () -> {
            theater.reserve(john, -1, 4); 
        });    

        String expectedMessage = "Not able to find any showing for given sequence -1"; 
        assertEquals(expectedMessage, thrown.getMessage());
    }
}
