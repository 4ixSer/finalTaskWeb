package ua.nure.gnuchykh.web.command.sort;

import java.util.Comparator;

import ua.nure.gnuchykh.entity.subject.Flight;

public enum FlightComparatorEnum {

    SORTBYID {
        {
            this.command = new Comparator<Flight>() {
                @Override
                public int compare(Flight o1, Flight o2) {
                    return o1.getNamberFlight().compareTo(o2.getNamberFlight());
                }
            };
        }
    },
    SORTBYSTATUS {
        {
            this.command = new Comparator<Flight>() {
                @Override
                public int compare(Flight o1, Flight o2) {
                    return o1.getStatus().compareTo(o2.getStatus());
                }
            };
        }
    },
    SORTBYDATE {
        {
            this.command = new Comparator<Flight>() {
                @Override
                public int compare(Flight o1, Flight o2) {
                    return o1.getDate().compareTo(o2.getDate());
                }
            };
        }
    };

    Comparator<Flight> command;

    public Comparator<Flight> getComparator() {
        return command;
    }
}
