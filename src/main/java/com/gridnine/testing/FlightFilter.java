package com.gridnine.testing;

import com.gridnine.testing.filteringCondition.FlightFilteringCondition;

import java.util.List;
import java.util.stream.Collectors;

public class FlightFilter {

    //returns flights from list that meet the condition
    public List<Flight> filter(List<Flight> flightList, FlightFilteringCondition condition) {
        return flightList.parallelStream().filter(condition).collect(Collectors.toList());
    }
}
