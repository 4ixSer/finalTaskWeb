package ua.nure.gnuchykh.web.command.sort;

import java.util.Comparator;

import ua.nure.gnuchykh.entity.users.User;

public enum UserComparatorEnum {

    SORTBYLOGIN {
        {
            this.command = new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o1.getLogin().compareTo(o2.getLogin());
                }
            };
        }
    },
    SORTBYNAME {
        {
            this.command = new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            };
        }
    },
    SORTBYEMAIL {
        {
            this.command = new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o1.getEmail().compareTo(o2.getEmail());
                }
            };
        }
    },
    SORTBYROLE {
        {
            this.command = new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o1.getType().toString().compareTo(o2.getType().toString());
                }
            };
        }
    },
    SORTBYID {
        {
            this.command = new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            };
        }
    };

    Comparator<User> command;

    public Comparator<User> getComparator() {
        return command;
    }

}