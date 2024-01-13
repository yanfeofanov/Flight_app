package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SearchFlightBuilderImplTest {
List<Flight> flightsNormal;
List<Flight> testFlight;

public void setFlightsNormal(){
    flightsNormal = new ArrayList<>();
    LocalDateTime twoHours = LocalDateTime.now().plusHours(2);
    List<Segment> singleSegment = new ArrayList<>();
    singleSegment.add(new Segment(twoHours,twoHours.plusHours(3)));
    Flight flightNormal = new Flight(singleSegment);

    flightsNormal.add(flightNormal);
}

@BeforeEach
public void setTestFlight(){
    testFlight = new ArrayList<>();
    setFlightsNormal();
    testFlight.addAll(flightsNormal);
}

@Test
public void all(){
    List<Flight> expFlight = flightsNormal;
    SearchFlightBuilder searchFlightBuilder = new SearchFlightBuilderImpl(testFlight);

    List<Flight> flightList = searchFlightBuilder
            .departureCurrentPointTime()
            .arrivalDateEarlierDepartureDate()
            .totalTimeSpentOnEarthExceedsTwoHours()
            .flight();
   assertEquals(expFlight,flightList);
}


}
