package ua.nure.gnuchykh.entity.subject;

/**
 * ������������ ������������ ������ ������ ��� �����
 *
 * @author qny4ix
 *
 */
public enum Status {

    /**
     * ������, ��������, �������, � ���������, ������, ���������������, ���������
     *
     */

    OPEN(1), REJEJECTED(2), CANCELED(3), INPROGRESS(4), CLOSED(5), PROCESSED(6) ,SUBMITTED(7);

    private Integer value;

    /**
     * Constructor.
     *
     * @param values
     *            ��������
     */
    Status(final Integer values) {
        this.value = values;
    }

    /**
     * A method for comparing String values with an ENUM element. ������������
     *
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
   public static Status fromValue(final int i) {
       for (Status c : Status.values()) {
           if (c.value.equals(i)) {
               return c;
           }
       }
       throw new IllegalArgumentException();
   }
}
