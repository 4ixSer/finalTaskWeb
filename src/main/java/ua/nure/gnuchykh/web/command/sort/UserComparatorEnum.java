package ua.nure.gnuchykh.web.command.sort;

import java.util.Comparator;

import ua.nure.gnuchykh.entity.users.User;

/**
 * Holder for messages of sort User.
 *
 * @author qny4ix
 *
 */
public enum UserComparatorEnum {
    /**
     * Sort by LOGIN.
     */
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
    /**
     * Sort by NAME.
     */
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
    /**
     * Sort by EMAIL.
     */
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
    /**
     * Sort by ROLE.
     */
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
    /**
     * Sort by ID.
     */
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

    protected Comparator<User> command;

    public Comparator<User> getComparator() {
        return command;
    }

}