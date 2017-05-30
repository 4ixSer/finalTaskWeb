package ua.nure.gnuchykh.util;

public final class Validation {

    private static final String simpleSrtingRegex = "^[а-яА-ЯёЁa-zA-Z][а-яА-ЯёЁa-zA-Z0-9-_]{1,20}$";

    /**
     * Проверяет вродяшие параметры на соответсвие простому патерну. Возврашает true каккието страныные символы
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
