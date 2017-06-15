package ua.nure.gnuchykh.entity.users;
/**
 * Enumeration of possible user roles.
 *
 * @author qny4ix
 *
 */
public enum ClientType {
    /**
     * Гость.
     * Водитель.
     * Диспечер.
     * Администратор.
     *
     */
    GUEST(0), DRIVER(3), DISPATCHER(2), ADMINISTRATOR(1);

    /**
     * Unique indicators of roles.
     */
    private Integer value;


    ClientType(final Integer values) {
        this.value = values;
    }


    public boolean equalsTo(final Integer name) {
        return value.equals(name);
    }


    public Integer value() {
        return value;
    }

    /**
     * Getting ClientType by its unique indicator.
     */
    public static ClientType fromValue(final int i) {
        for (ClientType c : ClientType.values()) {
            if (c.value.equals(i)) {
                return c;
            }
        }
        throw new IllegalArgumentException();
    }
}
