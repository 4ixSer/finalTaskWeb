package ua.nure.gnuchykh.entity.subject;

/**
 * Enumeration showing the status of the request or flight.
 *
 * @author qny4ix
 *
 */
public enum Status {

    /**
     * открыт, отклонен, отменен.
     * в прогрессе, закрыт, обрабатываеться,
     * отправлен
     *
     */

    OPEN(1), REJECTED(2), CANCELED(3), INPROGRESS(4), CLOSED(5), PROCESSED(6), SUBMITTED(7);

    /**
     * Getting Status by its unique indicator.
     */
    private Integer value;

    /**
     * Constructor.
     *
     * @param values
     *            значение
     */
    Status(final Integer values) {
        this.value = values;
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
    public static Status fromValue(final int i) {
        for (Status c : Status.values()) {
            if (c.value.equals(i)) {
                return c;
            }
        }
        throw new IllegalArgumentException();
    }
}
