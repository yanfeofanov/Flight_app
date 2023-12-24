package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.util.List;

public interface SearchFlightBuilder {

    List<Flight> flight();

    SearchFlightBuilder departureCurrentPointTime();

    SearchFlightBuilder arrivalDateEarlierDepartureDate();

    SearchFlightBuilder totalTimeSpentOnEarthExceedsTwoHours();


}
