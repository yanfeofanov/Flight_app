package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SearchFlightBuilderImpl implements SearchFlightBuilder {

    private List<Flight> flights;

    public SearchFlightBuilderImpl(List<Flight> flights) {
        this.flights = new ArrayList<>(flights);
    }

    @Override
    public List<Flight> flight() {
        return flights;
    }

    @Override
    public SearchFlightBuilder departureCurrentPointTime() {
        flights.removeIf(f -> f.getSegments().stream().anyMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now())));
        return this;
    }

    @Override
    public SearchFlightBuilder arrivalDateEarlierDepartureDate() {
        flights.removeIf(f -> f.getSegments().stream().anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())));
        return this;
    }

    @Override
    public SearchFlightBuilder totalTimeSpentOnEarthExceedsTwoHours() {
        flights.removeIf(f -> {
            List<Segment> segments = f.getSegments();
            LocalDateTime department;
            LocalDateTime arrival;
            Duration duration = Duration.ZERO;
            for(int i = 1; i < segments.size(); i++){
                department = segments.get(i).getDepartureDate();
                arrival = segments.get(i-1).getArrivalDate();
                duration = duration.plus(Duration.between(department,arrival).abs());
            }
            return  duration.toHours() >= 2;
        });
        return this;
    }
}
