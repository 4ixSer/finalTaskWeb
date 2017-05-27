package ua.nure.gnuchykh.web.command.sort;

import java.util.Comparator;

import ua.nure.gnuchykh.entity.cars.Car;

public enum CarComparatorEnum {

    SORTBYTYPE {
        {
            this.command = new Comparator<Car>() {
                @Override
                public int compare(Car o1, Car o2) {
                    return o1.getType().toString().compareTo(o2.getType().toString());
                }
            };
        }
    },
    SORTBYCARRYING {
        {
            this.command = new Comparator<Car>() {
                @Override
                public int compare(Car o1, Car o2) {
                    return o1.getCarryingCar().compareTo(o2.getCarryingCar());
                }
            };
        }
    },
    SORTBYAMOUNT {
        {
            this.command = new Comparator<Car>() {
                @Override
                public int compare(Car o1, Car o2) {
                    return o1.getAmountCar().compareTo(o2.getAmountCar());
                }
            };
        }
    },
    SORTBYENGINE {
        {
            this.command = new Comparator<Car>() {
                @Override
                public int compare(Car o1, Car o2) {
                    return o1.getEnginePower().compareTo(o2.getEnginePower());
                }
            };
        }
    },
    SORTBYSTATUS {
        {
            this.command = new Comparator<Car>() {
                @Override
                public int compare(Car o1, Car o2) {
                    return o1.getStatusCar().toString().compareTo(o2.getStatusCar().toString());
                }
            };
        }
    },
    SORTBYNAMBER {
        {
            this.command = new Comparator<Car>() {
                @Override
                public int compare(Car o1, Car o2) {
                    return o1.getNamber().toString().compareTo(o2.getNamber().toString());
                }
            };
        }
    };

    Comparator<Car> command;

    public Comparator<Car> getComparator() {
        return command;
    }

}
