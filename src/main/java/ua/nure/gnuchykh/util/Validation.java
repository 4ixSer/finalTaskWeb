package ua.nure.gnuchykh.util;

import java.time.LocalDateTime;

import ua.nure.gnuchykh.entity.cars.Status;
import ua.nure.gnuchykh.entity.cars.TYPE;
import ua.nure.gnuchykh.entity.users.ClientType;

public final class Validation {

    private static final String simpleSrtingRegex = "^[а-яА-ЯёЁa-zA-Z][а-яА-ЯёЁa-zA-Z0-9-_]{1,20}$";
    private static final String comentRegex = "^[а-яА-ЯёЁa-zA-Z][а-яА-ЯёЁa-zA-Z0-9-_. ]{1,50}$";
    private static final String mailRegex = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    private static final String loginRegex = "[A-Za-zА-Яа-яёЁ0-9]{5,20}";
    private static final String passwordRegex = "[A-Za-zА-Яа-яёЁ0-9]{3,20}";
    private static final String namberCarRegex = "[A-Z]{2}[0-9]{4}[A-Z]{2}";

    /**
     * Проверяет вродяшие параметры на соответсвие простому патерну. Возврашает
     * true каккието страныные символы
     *
     * @param values
     * @return
     */

    public static boolean parameterStringIsCorrect(String... values){

        for (String value : values) {
            if (value==null||value.isEmpty()){
                return false;
            }
        }
        return true;

    }

    public static boolean validateString(String... values) {

        for (String value : values) {
            if (!value.matches(simpleSrtingRegex)) {
                return false;
            }
        }
        return true;
    }

    public static boolean mailIsCorrect(String email) {
        if (!email.matches(mailRegex)) {
            return false;
        }
        return true;
    }

    public static boolean loginIsCorrect(String login) {
        if (!login.matches(loginRegex)) {
            return false;
        }
        return true;
    }

    public static boolean passwordIsCorrect(String password) {
        if (!password.matches(passwordRegex)) {
            return false;
        }
        return true;
    }

    public static boolean namberCarIsCorrect(String namber) {
        if (!namber.matches(namberCarRegex)) {
            return false;
        }
        return true;
    }


    public static boolean roleIsCorrect(Integer role) {
        if (role<ClientType.ADMINISTRATOR.value()||role>ClientType.DRIVER.value()) {
            return false;
        }
        return true;
    }

    public static boolean validateDouble(Double... values) {

        for (Double value : values) {
            if(value<0) {
                return false;
            }
        }
        return true;
    }

    public static boolean comentIsCorrect(String coment) {
        if(coment == null || coment.isEmpty() ) {
            return true;
        } else if (!coment.matches(comentRegex)) {
            return false;
        }
        return true;
    }

    public static boolean typeCarIsCorrect(Integer typeCar) {
        if (typeCar<TYPE.PLATFORM.value()||typeCar>TYPE.OTHERS.value()) {
            return false;
        }
        return true;
    }

    public static boolean statusCarIsCorrect(Integer statusCar) {
        if (statusCar<Status.FREE.value()||statusCar>Status.BROKEN.value()) {
            return false;
        }
        return true;
    }

    public static boolean localDateTimeIsCorrect(LocalDateTime time) {
        if(time.isAfter(LocalDateTime.now())){
            return true;
        }else {
            return false;
        }

    }

}
