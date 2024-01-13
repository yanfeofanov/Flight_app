package com.gridnine.testing;

import com.gridnine.testing.dao.FlightBuilder;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.SearchFlightBuilderImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> flightBeforeNow = (List<Flight>) new SearchFlightBuilderImpl(flights)
                .departureCurrentPointTime()
                .flight();
        List<Flight> flightArrivalDate = new SearchFlightBuilderImpl(flights)
                .arrivalDateEarlierDepartureDate()
                .flight();
        List<Flight> twoHoursDate = new SearchFlightBuilderImpl(flights)
                .totalTimeSpentOnEarthExceedsTwoHours()
                .flight();

        System.out.println("Before now \n" + flightBeforeNow);
        System.out.println("Before Arrival \n" + flightArrivalDate);
        System.out.println("Two Hours \n"+ twoHoursDate);
    }
}