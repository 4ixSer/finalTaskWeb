package ua.nure.gnuchykh.web.command.sort;

import java.util.Comparator;

import ua.nure.gnuchykh.entity.subject.Flight;

/**
 * Holder for messages of sort command Flight.
 *
 * @author qny4ix
 *
 */
public enum FlightComparatorEnum {
    /**
     * Sort by ID.
     */
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
    /**
     * Sort by status.
     */
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
    /**
     * Sort by DATE.
     */
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

    protected Comparator<Flight> command;

    public Comparator<Flight> getComparator() {
        return command;
    }
}
