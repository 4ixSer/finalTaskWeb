package ua.nure.gnuchykh.exception;

/**
 * Holder for messages of exceptions.
 * @author qny4ix
 */
public class Messages {

    private Messages() {

    }

    public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";

    public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";

    public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";

    public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";

    public static final String ERR_CANNOT_CLOSE_PREPARED_STATEMENT = "Cannot close a Prepared Statement";

    public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";

    // Ошибки юзера
    public static final String ERR_CANNOT_OBTAIN_ALL_USERS = "Cannot obtain the all users";

    public static final String ERR_CANNOT_OBTAIN_USER_BY_ID = "Cannot obtain the user by Id";

    public static final String ERR_CANNOT_DELETE_USER = "Cannot delete the user by Id";

    public static final String ERR_CANNOT_CREATE_USER = "Cannot create the user";

    public static final String ERR_CANNOT_UPDATE_USER = "Cannot update the user";

    public static final String ERR_CANNOT_FIND_USER_BY_ID = "Cannot find the user by Id";

    // оибки заказа
    public static final String ERR_CANNOT_OBTAIN_ALL_REQUEST = "Cannot obtain the all request";

    public static final String ERR_CANNOT_OBTAIN_REQUEST_BY_ID = "Cannot obtain the request by Id";

    public static final String ERR_CANNOT_OBTAIN_REQUEST_BY_USER_ID = "Cannot obtain the request by user Id";

    public static final String ERR_CANNOT_DELETE_REQUEST = "Cannot delete the request by Id";

    public static final String ERR_CANNOT_CREATE_REQUEST = "Cannot create the request";

    public static final String ERR_CANNOT_UPDATE_REQUEST = "Cannot update the request";

    public static final String ERR_CANNOT_FIND_FIRST_REQUEST = "Cannot find the first request";

    // ошибки рейса
    public static final String ERR_CANNOT_OBTAIN_ALL_FLIGHT = "Cannot obtain the all flight";

    public static final String ERR_CANNOT_OBTAIN_FLIGHT_BY_ID = "Cannot obtain the flight by Id";

    public static final String ERR_CANNOT_DELETE_FLIGHT = "Cannot delete the flight by Id";

    public static final String ERR_CANNOT_CREATE_FLIGHT = "Cannot create the flight";

    public static final String ERR_CANNOT_UPDATE_FLIGHT = "Cannot update the flight";

    public static final String ERR_CANNOT_FIND_FLIGHT_BY_DRIVER = "Cannot find the flight by user";

    public static final String ERR_CANNOT_FIND_FLIGHT_BY_CAR = "Cannot find the flight by car";

    // Ошибки машин
    public static final String ERR_CANNOT_OBTAIN_ALL_CAR = "Cannot obtain the all car";

    public static final String ERR_CANNOT_OBTAIN_CAR_BY_ID = "Cannot obtain the car by Id";

    public static final String ERR_CANNOT_DELETE_CAR = "Cannot delete the car by Id";

    public static final String ERR_CANNOT_CREATE_CAR = "Cannot create the car";

    public static final String ERR_CANNOT_UPDATE_CAR = "Cannot update the car";

    public static final String ERR_CANNOT_FIND_CAR_BY_NAMBER = "Cannot find the car by namber";

    public static final String ERR_CANNOT_FIND_AVG = "Cannot find AVG";

    public static final String ERR_CANNOT_FIND_CAR_BY_CHARACTERISTICS = "Cannot find the car by characteristics";

    //Ошибки приложений

    public static final String ERR_LOGIN_OR_PASSWORD_EMPTY = " Login/password cannot be empty";

    public static final String ERR_ROLLBACK = "Eroro rollback";
}