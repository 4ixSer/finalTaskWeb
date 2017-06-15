package ua.nure.gnuchykh.entity.cars;


/**
 * Enumeration of machine types.
 *
 * @author qny4ix
 *
 */
public enum TYPE {
    /**
     * ���������        PLATFORM
     * ������           VAN
     * ��������         TANK
     * ��������         Tipper
     * �������������    SORTEMENT
     * �������������    CONTAINER
     * ����             BOARD
     * ��������         GASOLINE
     * ����������       TENT
     * ������           Truck
     * ������������     REFRIGERATOR
     * ������
     */
    PLATFORM(1), VAN(2), TANK(3), TIPPER(4), SORTEMENT(5), CONTAINER(6), BOARD(7), GASOLINE(8), TENT(9), TRUCK(10), REFRIGERATOR(11), OTHERS(12);

    /**
     * Unique indicators of types.
     */
    private Integer value;

    /**
     * Constructor.
     *
     * @param values
     *            ��������
     */
    TYPE(final Integer values) {
        this.value = values;
    }

    /**
     * A method for comparing String values with an ENUM element.
     *������������
     * @param name
     *            �������� String
     * @return True if matched, false if not matched
     */
    public boolean equalsTo(final Integer name) {
        return value.equals(name);
    }

    /**
     * A method to obtain a String from an ENUM element.
     *
     * @return �������� String
     */
    public Integer value() {
        return value;
    }

    /**
     * Method for searching for a value by Int.
     *
     * @param i
     *            The required String element
     * @return
     */
    public static TYPE fromValue(final int i) {
        for (TYPE c : TYPE.values()) {
            if (c.value.equals(i)) {
                return c;
            }
        }
        throw new IllegalArgumentException();
    }

}
