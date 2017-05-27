package ua.nure.gnuchykh.web.command.sort;

import java.util.Comparator;

import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.entity.users.User;

public class SortFactory {
    public static Comparator<Car>  getCarComparator(String command) {
        if (command == null || command.isEmpty()) {
            // если команда не задана в текущем запросе
            return null;
        }

        CarComparatorEnum sortEnum = CarComparatorEnum.valueOf(command.toUpperCase());
        return  sortEnum.getComparator();

    }

    public static Comparator<User>  getUserComparator(String command) {
        if (command == null || command.isEmpty()) {
            // если команда не задана в текущем запросе
            return null;
        }

        UserComparatorEnum sortEnum = UserComparatorEnum.valueOf(command.toUpperCase());
        return  sortEnum.getComparator();

    }


}
