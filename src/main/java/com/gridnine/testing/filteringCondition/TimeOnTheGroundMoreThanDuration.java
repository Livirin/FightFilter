package com.gridnine.testing.filteringCondition;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;

import java.time.Duration;
import java.util.List;

//Condition for finding a flight that has total time between segments more than set duration

public class TimeOnTheGroundMoreThanDuration implements FlightFilteringCondition {

    private Duration duration;

    public TimeOnTheGroundMoreThanDuration(Duration duration) {
        this.duration = duration;
    }

    @Override
    public boolean test(Flight flight) {
        List<Segment> segments = flight.getSegments();
        int size = segments.size();
        Duration timeOnTheGround = Duration.ZERO;
        for (int i = 1; i < size; i++) {
            timeOnTheGround = timeOnTheGround.plus(Duration.between(segments.get(i-1).getArrivalDate(), segments.get(i).getDepartureDate()));
        }
        return timeOnTheGround.compareTo(duration) > 0;
    }
}
