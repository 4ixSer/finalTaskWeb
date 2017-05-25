package ua.nure.gnuchykh.entity.users;

public enum ClientType {

    GUEST(0), DRIVER(3), DISPATCHER(2), ADMINISTRATOR(1);

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


    public static ClientType fromValue(final int i) {
        for (ClientType c : ClientType.values()) {
            if (c.value.equals(i)) {
                return c;
            }
        }
        throw new IllegalArgumentException();
    }
}
