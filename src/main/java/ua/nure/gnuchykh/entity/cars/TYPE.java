package ua.nure.gnuchykh.entity.cars;


/**
 * Тип Грузовиков. ВОзможная уязвимость конечного продукта иза невозможности добавить другой тип
 * @author qny4i
 *
 */
public enum TYPE {
    /**
     * ПЛАТФОРМА        PLATFORM
     * ФУРГОН           VAN
     * ЦИСТЕРНА         TANK
     * САМОСВАЛ         Tipper
     * СОРТИМЕНТОВОЗ    SORTEMENT
     * КОНТЕЙНЕРОВОЗ    CONTAINER
     * БОРТ             BOARD
     * БЕНЗОВОЗ         GASOLINE
     * ТЕНТОВАНЫЙ       TENT
     * АВТОВО           Truck
     * РЕФРЕЖЕРАТОР     REFRIGERATOR
     * Другое
     */
    PLATFORM(1), VAN(2), TANK(3), TIPPER(4), SORTEMENT(5), CONTAINER(6), BOARD(7), GASOLINE(8), TENT(9), TRUCK(10), REFRIGERATOR(11), OTHERS(12);

    private Integer value;

    /**
     * Constructor.
     *
     * @param values
     *            значение
     */
    TYPE(final Integer values) {
        this.value = values;
    }

    /**
     * A method for comparing String values with an ENUM element.
     *внутренности
     * @param name
     *            значение String
     * @return True if matched, false if not matched
     */
    public boolean equalsTo(final Integer name) {
        return value.equals(name);
    }

    /**
     * A method to obtain a String from an ENUM element.
     *
     * @return значение String
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
