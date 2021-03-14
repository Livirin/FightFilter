package com.gridnine.testing;

import com.gridnine.testing.filteringCondition.DepartureBeforeDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Random;

public class DepartureBeforeDateTimeTest {

    private DepartureBeforeDateTime filter;

    private LocalDateTime dateTime;

    @Before
    public void createFilter() {
        dateTime = LocalDateTime.now();
        filter = new DepartureBeforeDateTime(dateTime);
    }

    @Test
    public void departureShouldBeBeforeDateTime() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        Flight flight = FlightBuilder.createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow);

        Assert.assertTrue(filter.test(flight));
    }

    @Test
    public void departureShouldNotBeBeforeDateTime() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        Flight flight = FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(6));

        Assert.assertFalse(filter.test(flight));
    }

    @Test
    public void checkDepartureIsBeforeDateTime() {
        Random random = new Random();
        LocalDateTime departureDate = dateTime.minusDays(2).plusDays(random.nextInt(5));
        Flight flight = FlightBuilder.createFlight(departureDate, departureDate.plusHours(5));

        Assert.assertEquals(departureDate.isBefore(dateTime), filter.test(flight));
    }
}
