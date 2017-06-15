package ua.nure.gnuchykh.web.command.sort;

import java.util.Comparator;

import ua.nure.gnuchykh.entity.cars.Car;

/**
 * Holder for messages of sort command Car.
 *
 * @author qny4ix
 *
 */
public enum CarComparatorEnum {
    /**
     * Sort by type.
     */
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
    /**
     * Sorting Carrying capacity of the machine.
     */
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
    /**
     * Sort by ID.
     */
    SORTBYID {
        {
            this.command = new Comparator<Car>() {
                @Override
                public int compare(Car o1, Car o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            };
        }
    },
    /**
     * Sort by volume.
     */
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
    /**
     * Sort by engine power.
     */
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
    /**
     * Sort by status.
     */
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
    /**
     * Sort by machine number.
     */
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

    protected Comparator<Car> command;

    public Comparator<Car> getComparator() {
        return command;
    }

}
