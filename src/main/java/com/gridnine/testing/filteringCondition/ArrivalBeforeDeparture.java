package com.gridnine.testing.filteringCondition;

import com.gridnine.testing.Flight;

//Condition for finding a flight having any segment with arrival date before a departure date

public class ArrivalBeforeDeparture implements FlightFilteringCondition {

    @Override
    public boolean test(Flight flight) {
        return flight.getSegments().stream().anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()));
    }
}
