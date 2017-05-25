package ua.nure.gnuchykh.entity.cars;


/**
 * Тип Грузовиков. ВОзможная уязвимость конечного продукта иза невозможности добавить другой тип
 * @author qny4i
 *
 */
public enum TYPE {
    /**
     * ПЛАТФОРМА
     * ФУРГОН
     * ЦИСТЕРНА
     * САМОСВАЛ
     * СОРТИМЕНТОВОЗ
     * КОНТЕЙНЕРОВОЗ
     * БЕНЗОВОЗ
     * ТЕНТОВАНЫЙ
     * АВТОВО
     * РЕФРЕЖЕРАТОР
     * Другое
     */
    PLATFORM(1), VAN(2), TANK(3), SUGGESTION(4), SORTEMENT(5), BOARD(6),
    CONTAINER(7), GASOLINE(8), TANKER(9), TENT(10), AVTOVOSCH(11), REFRIGERATOR(12), OTHERS(13);

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
