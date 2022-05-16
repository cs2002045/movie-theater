package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Movie {

    private static final int MOVIE_CODE_SPECIAL = 1;
    private static final double MOVIE_SPECIAL_DISCOUNT_PERCENTAGE = 0.2d;
    private static final double MOVIE_ELEVEN_TO_FOUR_DISCOUNT_PERCENTAGE = 0.25d;
    private static final double MOVIE_FIRST_SHOW_DISCOUNT_AMOUNT = 3;
    private static final double MOVIE_SECOND_SHOW_DISCOUNT_AMOUNT = 2;
    private static final double MOVIE_SEVENTH_SHOW_DISCOUNT_AMOUNT = 1;

    private static final int FIRST_SHOW = 1;
    private static final int SECOND_SHOW = 2;
    private static final int SEVENTH_SHOW = 7;

    private static final Map<Integer, Double> SPECIAL_DISCOUNTS;  
    static {
        SPECIAL_DISCOUNTS = new HashMap<>();
        SPECIAL_DISCOUNTS.put(MOVIE_CODE_SPECIAL, MOVIE_SPECIAL_DISCOUNT_PERCENTAGE);
    }

    private static final Map<Integer, Double> SHOW_SEQUENCE_DISCOUNTS;
    static {
        SHOW_SEQUENCE_DISCOUNTS = new HashMap<>();
        SHOW_SEQUENCE_DISCOUNTS.put(FIRST_SHOW, MOVIE_FIRST_SHOW_DISCOUNT_AMOUNT);
        SHOW_SEQUENCE_DISCOUNTS.put(SECOND_SHOW, MOVIE_SECOND_SHOW_DISCOUNT_AMOUNT);
        SHOW_SEQUENCE_DISCOUNTS.put(SEVENTH_SHOW, MOVIE_SEVENTH_SHOW_DISCOUNT_AMOUNT);
    }

    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public double calculateTicketPrice(Showing showing) {
        return ticketPrice - getDiscount(showing);
    }

    private double getDiscount(Showing showing) {
        double specialDiscount = 0;
        if (SPECIAL_DISCOUNTS.keySet().contains(specialCode)) {
            specialDiscount = ticketPrice * SPECIAL_DISCOUNTS.get(specialCode);
        }

        double sequenceDiscount = 0;
        if (SHOW_SEQUENCE_DISCOUNTS.containsKey(showing.getSequenceOfTheDay())) {
            sequenceDiscount = SHOW_SEQUENCE_DISCOUNTS.get(showing.getSequenceOfTheDay());
        }

        double showTimeDiscount = getTimeDiscount(showing);

        return biggestDiscount(specialDiscount, sequenceDiscount, showTimeDiscount);
    }

    private double getTimeDiscount(Showing showing) {
        
        LocalDateTime from = LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0));
        LocalDateTime to = LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0));
        Duration discountWindow = Duration.between(from, to);
        Duration hoursFromEleven = Duration.between(from, showing.getStartTime());
        if (hoursFromEleven.toHours() >= 0 && hoursFromEleven.toHours() <= discountWindow.toHours()) {
            return ticketPrice * MOVIE_ELEVEN_TO_FOUR_DISCOUNT_PERCENTAGE;
        }

        return 0;
    }

    private double biggestDiscount(double specialDiscount, double sequenceDiscount, double showTimeDiscount) {
        return Math.max(Math.max(specialDiscount, sequenceDiscount), showTimeDiscount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }
}