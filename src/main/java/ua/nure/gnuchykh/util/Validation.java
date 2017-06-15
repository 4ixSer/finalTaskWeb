package ua.nure.gnuchykh.util;

import java.time.LocalDateTime;

import ua.nure.gnuchykh.entity.cars.Status;
import ua.nure.gnuchykh.entity.cars.TYPE;
import ua.nure.gnuchykh.entity.users.ClientType;

/**
 * Holder for Validation.
 *
 * @author qny4ix
 *
 */
public final class Validation {

    private Validation() {

    }

    private static final String simpleSrtingRegex = "^[à-ÿÀ-ß¸¨a-zA-Z][à-ÿÀ-ß¸¨a-zA-Z0-9-_]{1,20}$";
    private static final String comentRegex = "^[à-ÿÀ-ß¸¨a-zA-Z][à-ÿÀ-ß¸¨a-zA-Z0-9-_. ]{1,50}$";
    private static final String mailRegex = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    private static final String loginRegex = "[A-Za-zÀ-ßà-ÿ¸¨0-9]{5,20}";
    private static final String passwordRegex = "[A-Za-zÀ-ßà-ÿ¸¨0-9]{3,20}";
    private static final String namberCarRegex = "[A-Z]{2}[0-9]{4}[A-Z]{2}";

    /**
     * The method checks the input parameters for zero and null.
     *
     * @param values
     *            A set of parameters.
     * @return True if they are not empty. False if at least 1 parameter does
     *         not match.
     */
    public static boolean parameterStringIsCorrect(final String... values) {

        for (String value : values) {
            if (value == null || value.isEmpty()) {
                return false;
            }
        }
        return true;

    }

    /**
     * Matching the input data with a simpleSrtingRegex.
     *
     * @param values
     *            A set of parameters.
     * @return True if it matches. False no.
     */
    public static boolean validateString(final String... values) {

        for (String value : values) {
            if (!value.matches(simpleSrtingRegex)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Matching email mailRegex.
     *
     * @param email
     *            email
     * @return True if it matches. False no.
     */
    public static boolean mailIsCorrect(final String email) {
        if (!email.matches(mailRegex)) {
            return false;
        }
        return true;
    }

    /**
     * Matching login to a loginRegex.
     *
     * @param login
     *            login
     * @return True if it matches. False no.
     */
    public static boolean loginIsCorrect(final String login) {
        if (!login.matches(loginRegex)) {
            return false;
        }
        return true;
    }

    /**
     * Matching the password is passwordRegex.
     *
     * @param password
     *            password
     * @return True if it matches. False no.
     */
    public static boolean passwordIsCorrect(final String password) {
        if (!password.matches(passwordRegex)) {
            return false;
        }
        return true;
    }

    /**
     * Matching the namberÑAR is namberCarRegex.
     *
     * @param namber namber
     * @return True if it matches. False no.
     */
    public static boolean namberCarIsCorrect(final String namber) {
        if (!namber.matches(namberCarRegex)) {
            return false;
        }
        return true;
    }

    /**
     * Matching the role Is Correct.
     *
     * @param role
     *            role
     * @return True if it Correct. False no.
     */
    public static boolean roleIsCorrect(final Integer role) {
        if (role < ClientType.ADMINISTRATOR.value() || role > ClientType.DRIVER.value()) {
            return false;
        }
        return true;
    }

    /**
     * Matching the Doublenamber is Correct.
     *
     * @param values
     *            A set of Double.
     * @return True if it Correct. False no.
     */
    public static boolean validateDouble(final Double... values) {

        for (Double value : values) {
            if (value < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Matching the coment is Correct.
     *
     * @param coment
     *            coment
     * @return True if it Correct. False no.
     */
    public static boolean comentIsCorrect(final String coment) {
        if (coment == null || coment.isEmpty()) {
            return true;
        } else if (!coment.matches(comentRegex)) {
            return false;
        }
        return true;
    }

    /**
     * Matching the typeCar is Correct.
     *
     * @param typeCar
     *            typeCar
     * @return True if it Correct. False no.
     */
    public static boolean typeCarIsCorrect(final Integer typeCar) {
        if (typeCar < TYPE.PLATFORM.value() || typeCar > TYPE.OTHERS.value()) {
            return false;
        }
        return true;
    }

    /**
     * Matching the statusCar is Correct.
     *
     * @param statusCar
     *            statusCar
     * @return True if it Correct. False no.
     */
    public static boolean statusCarIsCorrect(final Integer statusCar) {
        if (statusCar < Status.FREE.value() || statusCar > Status.BROKEN.value()) {
            return false;
        }
        return true;
    }

    /**
     * Matching the time is Correct.
     *
     * @param time
     *            time
     * @return True if it time> time.now. False no.
     */
    public static boolean localDateTimeIsCorrect(final LocalDateTime time) {
        return time.isAfter(LocalDateTime.now());

    }

    /**
     * Matching the local is Correct.
     *
     * @param local
     *            local
     * @return True if it Correct. False no.
     */
    public static boolean languageIsCorrect(final String local) {

        return local.equals("ru_RU") || local.equals("en_US");

    }

}
