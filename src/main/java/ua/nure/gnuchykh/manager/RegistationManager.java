package ua.nure.gnuchykh.manager;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.CarDAO;
import ua.nure.gnuchykh.DAO.UserDAO;
import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.entity.cars.Status;
import ua.nure.gnuchykh.entity.cars.TYPE;
import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.entity.users.User;

public class RegistationManager {

    private static final Logger LOG = Logger.getLogger(RegistationManager.class);

    public static boolean registaticonNewUser(String login, String pass, String name, String email, Integer role) {

        LOG.debug("Создать нового юзера " + login);
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findEntityByLogin(login);
        if (user != null) {
            LOG.info("Такой логн уже есть");
            return false;
        }
        User newUser = new User(login, pass, name, email, ClientType.fromValue(role));

        return userDAO.create(newUser);
    }

    public static boolean registaticonNewCar(String namber, TYPE type, Double carryingCar, Double amountCar,
            Double enginePower, Status statusCar, String comments) {

        LOG.debug("Создать новую машину ");
        CarDAO dao = new CarDAO();
        Car car = dao.findEntityByNamber(namber);

        if (car != null) {
            LOG.info("Такой норем машины уже есть");
            return false;
        }
        Car newCar = new Car(namber, type, carryingCar, amountCar, enginePower, statusCar, comments);

        return dao.create(newCar);
    }

}
