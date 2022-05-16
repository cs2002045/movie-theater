package com.jpmc.theater;

import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    // public double getMovieFee() {
    //     return movie.getTicketPrice();
    // }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    @Override
    public String toString() {
        return this.getSequenceOfTheDay() + ": " + this.getStartTime() + " " + this.getMovie().getTitle() + " " 
            + CommonUtil.humanReadableFormat(this.getMovie().getRunningTime()) + " $" + this.getMovie().getTicketPrice();
    }

    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
    
}
