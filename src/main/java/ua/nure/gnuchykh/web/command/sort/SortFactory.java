package ua.nure.gnuchykh.web.command.sort;

import java.util.Comparator;

import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.entity.subject.Flight;
import ua.nure.gnuchykh.entity.subject.Request;
import ua.nure.gnuchykh.entity.users.User;

public class SortFactory {
    public static Comparator<Car> getCarComparator(String command) {
        CarComparatorEnum sortEnum = CarComparatorEnum.valueOf(command.toUpperCase());
        return sortEnum.getComparator();

    }

    public static Comparator<User> getUserComparator(String command) {
        UserComparatorEnum sortEnum = UserComparatorEnum.valueOf(command.toUpperCase());
        return sortEnum.getComparator();

    }

    public static Comparator<Request> getRequestComparator(String command) {
        RequestComparatorEnum sortEnum = RequestComparatorEnum.valueOf(command.toUpperCase());
        return sortEnum.getComparator();

    }

    public static Comparator<Flight> getFlightComparator(String command) {
        FlightComparatorEnum sortEnum = FlightComparatorEnum.valueOf(command.toUpperCase());
        return sortEnum.getComparator();

    }


}
