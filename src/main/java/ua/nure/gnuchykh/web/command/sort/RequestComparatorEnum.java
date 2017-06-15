package ua.nure.gnuchykh.web.command.sort;

import java.util.Comparator;

import ua.nure.gnuchykh.entity.subject.Request;

/**
 * Holder for messages of sort command Request.
 *
 * @author qny4ix
 *
 */
public enum RequestComparatorEnum {
    /**
     * Sort by ID.
     */
    SORTBYID {
        {
            this.command = new Comparator<Request>() {
                @Override
                public int compare(Request o1, Request o2) {
                    return o1.getNamberRequest().compareTo(o2.getNamberRequest());
                }
            };
        }
    },
    /**
     * Sort by DATEREQUEST.
     */
    SORTBYDATEREQUEST {
        {
            this.command = new Comparator<Request>() {
                @Override
                public int compare(Request o2, Request o1) {
                    return o1.getDateRequest().compareTo(o2.getDateRequest());
                }
            };
        }
    },
    /**
     * Sort by DEPARTURE.
     */
    SORTBYDATEDEPARTURE {
        {
            this.command = new Comparator<Request>() {
                @Override
                public int compare(Request o2, Request o1) {
                    return o1.getDateDeparture().compareTo(o2.getDateDeparture());
                }
            };
        }
    },
    /**
     * Sort by type.
     */
    SORTBYTYPE {
        {
            this.command = new Comparator<Request>() {
                @Override
                public int compare(Request o1, Request o2) {
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
            this.command = new Comparator<Request>() {
                @Override
                public int compare(Request o1, Request o2) {
                    return o1.getCarryingCar().compareTo(o2.getCarryingCar());
                }
            };
        }
    },
    /**
     * Sort by engine power.
     */
    SORTBYENGINEPOWER {
        {
            this.command = new Comparator<Request>() {
                @Override
                public int compare(Request o1, Request o2) {
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
            this.command = new Comparator<Request>() {
                @Override
                public int compare(Request o1, Request o2) {
                    return o1.getStatus().toString().compareTo(o2.getStatus().toString());
                }
            };
        }
    },
    /**
     * Sort by volume.
     */
    SORTBYAMOUNT {
        {
            this.command = new Comparator<Request>() {
                @Override
                public int compare(Request o1, Request o2) {
                    return o1.getAmountCar().compareTo(o2.getAmountCar());
                }
            };
        }
    };

    protected Comparator<Request> command;

    public Comparator<Request> getComparator() {
        return command;
    }
}
