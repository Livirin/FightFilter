package com.gridnine.testing;

import com.gridnine.testing.filteringCondition.ArrivalBeforeDeparture;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Random;

public class ArrivalBeforeDepartureTest {

    private ArrivalBeforeDeparture filter;

    @Before
    public void createFilter() {
        filter = new ArrivalBeforeDeparture();
    }

    @Test
    public void departureDateShouldBeBeforeArrivalDate() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        Flight flight = FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6));
        Assert.assertTrue(filter.test(flight));
    }

    @Test
    public void departureDateShouldNotBeBeforeArrivalDate() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        Flight flight = FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(6));
        Assert.assertFalse(filter.test(flight));
    }

    @Test
    public void checkDepartureDateIsBeforeArrivalDateRandom() {
        LocalDateTime dateTime = LocalDateTime.now().plusDays(2);
        Random random = new Random();
        LocalDateTime departureDate = dateTime.plusHours(random.nextInt(5));
        LocalDateTime arrivalDate = dateTime.plusHours(random.nextInt(5));
        Flight flight = FlightBuilder.createFlight(departureDate, arrivalDate);

        Assert.assertEquals(arrivalDate.isBefore(departureDate), filter.test(flight));
    }

}
