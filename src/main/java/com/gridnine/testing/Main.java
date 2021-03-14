package com.gridnine.testing;

import com.gridnine.testing.filteringCondition.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final List<Flight> flightList = FlightBuilder.createFlights();

        System.out.println("Все вылеты:");
        print(flightList);

        FlightFilter filter = new FlightFilter();

        System.out.println("Вылеты до текущего момента времени:");
        print(filter.filter(flightList, new DepartureBeforeDateTime(LocalDateTime.now())));

        System.out.println("Вылеты, имеющие сегменты с датой прилёта раньше даты вылета:");
        print(filter.filter(flightList, new ArrivalBeforeDeparture()));

        System.out.println("Общее время, проведённое на земле, превышает два часа:");
        print(filter.filter(flightList, new TimeOnTheGroundMoreThanDuration(Duration.ofHours(2))));
    }

    public static void print(List<Flight> data) {

        if (data.size() == 0)
            System.out.println("Нет результата, удовлетворяющего условию");
        else
            for (Flight flight: data) {
                System.out.println(flight);
            }
        System.out.println();
    }
}
