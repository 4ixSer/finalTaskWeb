package ua.nure.gnuchykh.util;

public final class Validation {

    private static final String simpleSrtingRegex = "^[�-��-߸�a-zA-Z][�-��-߸�a-zA-Z0-9-_]{1,20}$";

    /**
     * ��������� �������� ��������� �� ����������� �������� �������. ���������� true �������� ��������� �������
     * @param values
     * @return
     */
    public static boolean validateString(String... values) {

        for (String value : values) {
            if (!value.matches(simpleSrtingRegex)) {
                return true;
            }
        }
        return false;
    }
}
