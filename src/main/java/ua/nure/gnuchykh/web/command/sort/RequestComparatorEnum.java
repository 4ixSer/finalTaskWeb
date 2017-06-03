package ua.nure.gnuchykh.web.command.sort;

import java.util.Comparator;

import ua.nure.gnuchykh.entity.subject.Request;

public enum RequestComparatorEnum {
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

    Comparator<Request> command;

    public Comparator<Request> getComparator() {
        return command;
    }
}
