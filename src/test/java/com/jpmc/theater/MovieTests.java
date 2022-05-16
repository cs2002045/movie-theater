package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovieTests {

    @Test
    void specialMovieWith20PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)));
       
        double discount = .20;
        double expected = spiderMan.getTicketPrice() * (1 - discount);
        assertEquals(expected, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void timeWindow25PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0)));

        double discount = .25;
        double expected = spiderMan.getTicketPrice() * (1 - discount);
        assertEquals(expected, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void firstShowDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
        
        double discount = 3.0;
        double expected = spiderMan.getTicketPrice() - discount;
        assertEquals(expected, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void secondShowDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
        
        double discount = 2.0;
        double expected = spiderMan.getTicketPrice() - discount;
        assertEquals(expected, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void seventShowDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)));
        
        double discount = 1.0;
        double expected = spiderMan.getTicketPrice() - discount;
        assertEquals(expected, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void biggestDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)));
        
        double discount = 0.25;
        double expected = spiderMan.getTicketPrice() * (1 - discount);
        assertEquals(expected, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void testEquality() {
        Movie movie1 = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Movie movie2 = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        assertTrue(movie1.equals(movie2) && movie2.equals(movie1));
        assertTrue(movie1.hashCode() == movie2.hashCode());
    }

    @Test
    void testEqualityNegative() {
        Movie movie1 = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Object movie2 = new Object();
        assertFalse(movie1.equals(movie2) && movie2.equals(movie1));
        assertFalse(movie1.hashCode() == movie2.hashCode());
    }

    @Test
    void testSameObject() {
        Movie movie1 = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Movie movie2 = movie1;
        assertTrue(movie1.equals(movie2) && movie2.equals(movie1));
        assertTrue(movie1.hashCode() == movie2.hashCode());
    }
}
