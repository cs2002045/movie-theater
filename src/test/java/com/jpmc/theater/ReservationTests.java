package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    @Test
    void totalFee() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.now()
        );

        int audienceCount = 3;
        double discount = 3.0;
        double expected = (showing.getMovie().getTicketPrice() - discount) * audienceCount;
        assertTrue(expected == new Reservation(customer, showing, 3).totalFee());
    }

    @Test
    void printReservation() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.now()
        );
        String expected = "Reservation by: John Doe, Showing: Spider-Man: No Way Home, # of Tickets: 3";
        assertEquals(expected, new Reservation(customer, showing, 3).toString());
    }
}
