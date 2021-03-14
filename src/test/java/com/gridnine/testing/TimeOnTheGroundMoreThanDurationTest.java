package com.gridnine.testing;

import com.gridnine.testing.filteringCondition.TimeOnTheGroundMoreThanDuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

public class TimeOnTheGroundMoreThanDurationTest {

    private TimeOnTheGroundMoreThanDuration filter;

    private Duration duration;

    @Before
    public void createFilter() {
        duration = Duration.ofHours(2);
        filter = new TimeOnTheGroundMoreThanDuration(duration);
    }

    @Test
    public void timeOnTheGroundShouldBeMoreThanDuration() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);

        Flight flight = FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7));

        Assert.assertTrue(filter.test(flight));
    }

    @Test
    public void timeOnTheGroundShouldNotBeMoreThanDuration() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);

        Flight flight = FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(6));

        Assert.assertFalse(filter.test(flight));
    }

    @Test
    public void checkTimeOnTheGroundIsMoreThanDuration() {
        Random random = new Random();
        int timeBetweenSegments = random.nextInt(3) + 1;
        Duration standOnTheGround = Duration.ofHours(timeBetweenSegments);

        LocalDateTime departureDate = LocalDateTime.now().plusDays(2);
        Flight flight = FlightBuilder.createFlight(departureDate, departureDate.plusHours(2),
                departureDate.plusHours(2 + timeBetweenSegments), departureDate.plusHours(4 + timeBetweenSegments));

        Assert.assertEquals(standOnTheGround.compareTo(duration) > 0, filter.test(flight));
    }

}
