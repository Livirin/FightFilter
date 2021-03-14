package com.gridnine.testing.filteringCondition;

import com.gridnine.testing.Flight;

import java.time.LocalDateTime;


//Condition for finding a flight that departure date is before a set date

public class DepartureBeforeDateTime implements FlightFilteringCondition {

    private LocalDateTime dateTime;

    public DepartureBeforeDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean test(Flight flight) {
        return flight.getSegments().get(0).getDepartureDate().isBefore(dateTime);
    }
}
